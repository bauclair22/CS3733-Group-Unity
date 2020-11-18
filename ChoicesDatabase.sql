CREATE TABLE Choices(
    choiceID int NOT NULL,
    choice varchar2(20),
    ChoiceDescription varchar2(280),
    constraint PKChoiceID PRIMARY KEY (choiceID)
);

CREATE TABLE Alternatives(
    AltenativeID int NOT NULL,
    choiceID int NOT NULL,
    alternative varchar2(20),
    AltDescription varchar2(280),
    constraint PKAlternativeID PRIMARY KEY (AltenativeID),
    constraint FKAltChoiceID FOREIGN KEY (AltenativeID) references Choices(choiceID)
);

CREATE TABLE TeamMembers(
    memberID int,
    choiceID int,
    MemberName varchar2(20),
    MemberPassword varchar2(20),
    constraint PKmemberID PRIMARY KEY (memberID),
    constraint FKmemberChoiceID FOREIGN KEY (choiceID) references Choices(choiceID)
);

CREATE TABLE Reactions(
    ReactionID int,
    alternativeID int,
    teamMemberID int,
    reaction varchar2(8) CHECK (reaction='like' OR reaction='dislike'), 
    constraint PKreactionID PRIMARY KEY (ReactionID),
    constraint FKreactAltID FOREIGN KEY (alternativeID) references Alternatives(AltenativeID),
    constraint FKreactMemberID FOREIGN KEY (teamMemberID) references TeamMembers(memberID)
);

CREATE TABLE Feedbacks(
    feedbackID int,
    alternativeID int,
    teamMemberID int,
    timeComplete float,
    feedbacks varchar2(140),
    constraint PKfbID PRIMARY KEY (feedbackID),
    constraint FKfbAltID FOREIGN KEY (alternativeID) references Alternatives(AltenativeID),
    constraint FKfbMemberID FOREIGN KEY (teamMemberID) references TeamMembers(memberID)
);
