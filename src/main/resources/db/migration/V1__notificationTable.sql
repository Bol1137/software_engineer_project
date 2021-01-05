
CREATE TABLE notifications(
        id UUID NOT NULL PRIMARY KEY,
        templateName VARCHAR (25) NOT NULL,
        body VARCHAR(200) NOT NULL,
        lang VARCHAR (15) NOT NULL
);