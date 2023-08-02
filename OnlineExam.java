package oasis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

class Login extends JFrame implements ActionListener {
    JButton submitButton;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;

    Login() {
        userLabel = new JLabel();
        userLabel.setText("Username:");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("Password:");
        textField2 = new JPasswordField(8);
        submitButton = new JButton("SUBMIT");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(submitButton);
        add(newPanel, BorderLayout.CENTER);
        submitButton.addActionListener(this);
        setTitle("Login Form");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();

        if (!passValue.equals("")) {
            // Open the online test if the password is not empty
            new OnlineTestBegin(userValue);
        } else {
            // Display a message if the password is empty
            textField2.setText("Enter Password");
        }
    }
}

class OnlineTestBegin extends JFrame implements ActionListener {
    JLabel questionLabel;
    JRadioButton[] options;
    JButton submitButton, resultButton;
    ButtonGroup bg;
    int count = 0, current = 0;
    int[] correctAnswers = {1, 1, 2, 0, 2, 3, 1, 3, 2, 1}; // Correct answers for each question

    Timer timer = new Timer();
    JLabel timeLabel;

    OnlineTestBegin(String s) {
        super(s);
        questionLabel = new JLabel();
        add(questionLabel);
        bg = new ButtonGroup();
        options = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            add(options[i]);
            bg.add(options[i]);
        }

        submitButton = new JButton("Submit");
        resultButton = new JButton("Result");
        submitButton.addActionListener(this);
        resultButton.addActionListener(this);
        add(submitButton);
        add(resultButton);

        setQuestion(); // Set the first question

        questionLabel.setBounds(30, 40, 450, 20);
        for (int i = 0, j = 0; i < 90; i += 30, j++) {
            options[j].setBounds(50, 80 + i, 200, 20);
        }
        submitButton.setBounds(95, 240, 140, 30);
        resultButton.setBounds(270, 240, 150, 30);
        timeLabel = new JLabel("Time left: 600");
        timeLabel.setBounds(480, 20, 100, 20);
        add(timeLabel);
        // Set the custom light reddish color
        Color lightReddish = new Color(255, 200, 200);
        getContentPane().setBackground(lightReddish);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);

        // Start the timer to keep track of time
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 600;

            public void run() {
                timeLabel.setText("Time left: " + i);
                i--;
                if (i < 0) {
                    timer.cancel();
                    timeLabel.setText("Time Out");
                    showResult();
                }
            }
        }, 0, 1000);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Check the answer when the submit button is clicked
            checkAnswer();
        } else if (e.getSource() == resultButton) {
            // Show the result when the result button is clicked
            showResult();
        }
    }

    void setQuestion() {
        bg.clearSelection(); // Clear any previous selected option
        if (current >= 10) {
            questionLabel.setText("No more questions.");
            for (int i = 0; i < 4; i++) {
                options[i].setVisible(false);
            }
            submitButton.setEnabled(false);
            resultButton.setText("Result");
            resultButton.setEnabled(true);
            return;
        }

        // Display the question and options based on the current question index
        switch (current) {
        case 0:
            questionLabel.setText("Que1: What is the capital of France?");
            options[0].setText("Berlin");
            options[1].setText("Paris");
            options[2].setText("London");
            options[3].setText("Rome");
            break;
        case 1:
            questionLabel.setText("Que2: Which planet is known as the 'Red Planet'?");
            options[0].setText("Venus");
            options[1].setText("Mars");
            options[2].setText("Jupiter");
            options[3].setText("Saturn");
            break;
        case 2:
            questionLabel.setText("Que3: Who wrote the play 'Romeo and Juliet'?");
            options[0].setText("William Shakespeare");
            options[1].setText("Mark Twain");
            options[2].setText("Charles Dickens");
            options[3].setText("Jane Austen");
            break;
        case 3:
            questionLabel.setText("Que4: What is the chemical symbol for water?");
            options[0].setText("H2O");
            options[1].setText("CO2");
            options[2].setText("O2");
            options[3].setText("NaCl");
            break;
        case 4:
            questionLabel.setText("Que5: Which famous scientist developed the theory of general relativity?");
            options[0].setText("Isaac Newton");
            options[1].setText("Albert Einstein");
            options[2].setText("Marie Curie");
            options[3].setText("Galileo Galilei");
            break;
        case 5:
            questionLabel.setText("Que6: What is the largest mammal on Earth?");
            options[0].setText("Elephant");
            options[1].setText("Blue Whale");
            options[2].setText("Giraffe");
            options[3].setText("Hippopotamus");
            break;
        case 6:
            questionLabel.setText("Que7: Which gas is most abundant in the Earth's atmosphere?");
            options[0].setText("Nitrogen");
            options[1].setText("Oxygen");
            options[2].setText("Carbon Dioxide");
            options[3].setText("Hydrogen");
            break;
        case 7:
            questionLabel.setText("Que8: What is the chemical symbol for gold?");
            options[0].setText("Au");
            options[1].setText("Ag");
            options[2].setText("Fe");
            options[3].setText("Cu");
            break;
        case 8:
            questionLabel.setText("Que9: Who painted the 'Mona Lisa'?");
            options[0].setText("Pablo Picasso");
            options[1].setText("Vincent van Gogh");
            options[2].setText("Leonardo da Vinci");
            options[3].setText("Claude Monet");
            break;
        case 9:
            questionLabel.setText("Que10: What is the chemical formula for methane?");
            options[0].setText("CO2");
            options[1].setText("CH4");
            options[2].setText("H2O");
            options[3].setText("N2");
            break;
        default:
            break;
        }
    }

    void checkAnswer() {
        if (current >= 10) {
            return; // All questions are answered
        }

        // Check the selected option against the correct answer
        if (options[correctAnswers[current]].isSelected()) {
            count++; // Increase the score if the answer is correct
        }

        current++; // Move to the next question
        setQuestion(); // Display the next question
    }

    void showResult() {
        JOptionPane.showMessageDialog(this, "Score =" + count);
        System.exit(0); // Close the application after showing the result
    }
}

public class OnlineExam {
    public static void main(String[] args) {
        try {
            // Create the login form to start the exam
            Login form = new Login();
            form.setSize(400, 150);
            form.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
