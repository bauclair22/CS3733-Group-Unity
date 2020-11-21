package controllers;

import model.Choice;

import model.Alternative;

public class ControllerChain {// Need to add access to overarching model where choices are stored (Doesnt currently Exist)
	//TODO: add parsing, and finish other controllers
	public final static int FEEDBACK=0;
	public final static int CREATE_CHOICE=1;
	public final static int ENTER_ADMIN=2;
	public final static int ADMIN_SET_TIMER=3;
	public static boolean parseController(int code) {// A code is given to the parser, and it delegates to another controller according to code
		switch(code) {
			case FEEDBACK:{
				Alternative a=new Alternative("place","holder");// placeholder for parsing, this should just find the alternative feedback is being given on
				FeedbackController fc=new FeedbackController(a);
				return fc.success;	
			}
			case CREATE_CHOICE:{
				String desc="placeholder";
				Alternative[] a=null;
				int members=0;
				Choice c=new Choice(desc, a, members);
				ChoiceController cc=new ChoiceController(c);
				return cc.success;	
			}
			
			case ENTER_ADMIN:{
				//TODO: add functionality to enter the admin view
				return true;	
			}
			
			case ADMIN_SET_TIMER:{
				//TODO: add functionality to set autodeletion to occur
				return true;
			}
			
			default:{
				return false;
			}
		}
			
	}
}
