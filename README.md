# Android-Quiz-Application
Objective:
The Quiz application with multiple choice timed questions is designed to work on android platform. The application has two logins as Administrator (Quiz Master) and User (Quiz Taker). Both logins have different functionality explained in further paragraphs. Administrator is having pre defined credentials. On the other hand, User is registered from selecting the register button on the login screen. Once registered, user is logged in with their user credential.
Functionality:
1.	In Quiz Taker Login, User is provided with three options namely “Start Quiz”, “ScoreBoard” and “Log Off”.
•	User can start the Quiz on selecting the Start Quiz button.
•	User can view the score on selecting the ScoreBoard button.
•	User can log out from the window on selecting Log off button and return to the login page.
Functionalities Implemented in User Window:
•	Total of 5 questions are displayed for the user. Once the user select the submit button, the next question will appear on the screen.
•	Timer: A timer of 15 seconds is placed on the top of the screen. It displays the time in decremented order from 15 seconds.
•	Time Out: Screen Time Out message will display on the screen once 15 seconds are passed. Then user is provided with an option to select “Next” to move to the next question.
•	Correct/Incorrect Radio button: On selecting the correct option the screen will highlight the correct option with green color.
On selecting the incorrect option the screen will highlight the correct option with green color and the selected option with red color.
•	Correct/Incorrect Toast Message: The screen will display a correct/incorrect toast message upon selecting submit button after clicking any of the option.
•	Summary Window: The summary window will show the score achieved by the quiz taker which are stored in the database.

2.	In Quiz Master Login, Administrator/ Quiz Master have the right to view the scores of all the users by clicking on the ScoreBoard button.
Working:
Launching the Quiz application directs the user to the login page where new user needs to register for the quiz and then login to the application, registered user can directly login to the application and start the quiz. Application has an QuizMaster login with password as “admin” to view the scores of all the quiztakers.
Once the user/quiz taker is logged in, the user is directed to the window consisting of start quiz, scoreboard and log off option. Upon selecting start quiz, user moves to instruction window to start the quiz. Quiz consists of multiple choice question having four options with only one correct option. Once the User selects any option and clicks submit button, the correct option is displayed with green color and a toast message. The Score is updated accordingly if correct option is selected. There is a 15 seconds timer for each question which when expires shows a time up message and directs the user to move to next question. If the User does not select any option and tries to move to next question, the application will throw a toast message to select any option provided. After Successful completion of the quiz, the completion window shows the final score achieved. These scores are stored in the database to be viewed by the quiztaker and the quizmaster. Now, User has the option to either start the quiz again or log off on selecting “HomePage” button. User can view their scores of all rounds in scoreboard window.
		Tools Used:
1.	Android Studio – It is an integrated development environment (IDE) for android application designing and development.
2.	Java SE Development Kit – It is an implementation of java platform to support and compile the android applications.
