import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Quiz extends JFrame {

    JPanel panel;
    JPanel Bpanel;
    JPanel FullPanel;
    private JLabel question;
    private JRadioButton[] options;
    private JButton next;
    private JButton prev;
    private ButtonGroup group;
    boolean currCorrectAns = false;
    private String[] wrongQuestions = new String[10];
    private String[] correctAnswers = new String[10];

    private String[] questions = {
            "Q1. How many years did Prophet Muhammad (SAW) preach in Makkah before migrating to Madinah?",
            "Q2. Which battle is known as the first battle fought by Muslims in Islam?",
            "Q3. What was the first command revealed in the Quran?",
            "Q4. Which Sahabi is referred to as \"As-Siddiq\" (The Truthful)?",
            "Q5. Which Surah is known as the “Heart of the Quran”?",
            "Q6. How many Surahs in the Quran start with “Alif-Lam-Meem”?",
            "Q7. Which two Prophets were known as \"brothers\" because they were sent to the same people?",
            "Q8. What is the name of the treaty between the Prophet Muhammad (SAW) and the Quraish?",
            "Q9. Which Prophet is mentioned the most times in the Quran?",
            "Q10. What is the distance a traveler must cover to shorten Salah?",
    };

    private String[][] choices = {
            {"10 years", "13 years", "15 years", "12 years"},
            {"Battle of Uhud", "Battle of Khandaq", "Battle of Badr", "Battle of Tabuk"},
            {"Pray to your Lord", "Read in the name of your Lord.", "Fast for Allah's pleasure.", "Believe in Allah."},
            {"Umar ibn Al-Khattab (RA)", "Uthman ibn Affan (RA)", "Abu Bakr(RA)", "Ali ibn Abi Talib (RA)"},
            {"Surah Al-Baqarah", "Surah Yaseen", "Surah Ar-Rahman", "Surah Al-Mulk"},
            {"5", "6", "7", "8"},
            {"Prophet Musa (AS) and Prophet Harun (AS)", "Prophet Ibrahim (AS) and Prophet Lut (AS)", "Prophet Isa (AS) and Prophet Yahya (AS)", "Prophet Yusuf (AS) and Prophet Benjamin (AS)"},
            {"Treaty of Hudaybiyyah", "Treaty of Medina", "Treaty of Tabuk", "Treaty of Fath"},
            {"Prophet Isa (AS)", "Prophet Musa (AS)", "Prophet Yusuf (AS)", "Prophet Ibrahim (AS)"},
            {"80 km", "105 km", "75 km", "50 km"}
    };

    private int[] answers = {1, 2, 1, 2, 1, 1, 0, 0, 1, 2};

    private int currentQuestion = 0;
    private int score = 0;
    private int wrongIndex = 0;

    public Quiz() {
        panel = new JPanel(new BorderLayout());
        Bpanel = new JPanel(new BorderLayout());
        FullPanel = new JPanel();
        Bpanel.setLayout(new GridLayout(4,1));
        panel.setLayout(new GridLayout(1,2));
        FullPanel.setLayout(new GridLayout(5,2));
        this.setLayout(new FlowLayout());

        group = new ButtonGroup();
        question = new JLabel();
        FullPanel.add(question,BorderLayout.NORTH);


        prev = new JButton("Previous Question");
        prev.addActionListener(new prevHandler());


        options = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
            Bpanel.add(options[i]);
        }
        FullPanel.add(Bpanel,BorderLayout.CENTER);

        next = new JButton("Next Question ");
        next.addActionListener(new Handler());

        panel.add(next,BorderLayout.EAST);
        FullPanel.add(panel,BorderLayout.SOUTH);
        this.add(FullPanel);
        loadQuestion();
    }

    public void loadQuestion() {
        question.setText(questions[currentQuestion]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(choices[currentQuestion][i]);
        }
        group.clearSelection();
    }

    class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 4; i++) {
                if (options[i].isSelected()) {
                    if (i == answers[currentQuestion]) {
                        currCorrectAns = true;
                        score++;
                    }
                    else{
                        currCorrectAns = false;
                        if (wrongIndex < wrongQuestions.length) {
                            wrongQuestions[wrongIndex] = questions[currentQuestion]; // Store wrong question
                            correctAnswers[wrongIndex] = choices[currentQuestion][answers[currentQuestion]]; // Store correct answer
                            wrongIndex++;
                        }
                    }
                }
            }
            currentQuestion++;
            if(currentQuestion>0){
                panel.remove(next);
                panel.add(prev,BorderLayout.WEST);
                panel.add(next,BorderLayout.EAST);
                FullPanel.add(panel);
            }
            if (currentQuestion < questions.length) {
                loadQuestion();
            } else {
                String message = "Quiz Completed!\nYour Score: " + score + "/10\n";

                if (wrongIndex > 0) {
                    message += "\nQuestions you got wrong (with correct answers):\n";
                    for (int i = 0; i < wrongIndex; i++) {
                        message += "- " + wrongQuestions[i] + "\n  Correct Answer: " + correctAnswers[i] + "\n\n";
                    }
                } else {
                    message += "\nCongratulations! You answered all correctly.";
                }

                JOptionPane.showMessageDialog(null, message);

            }
        }
    }
    class prevHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (currentQuestion != 0) {
                if(currCorrectAns){
                    score--;
                }
                currentQuestion--;
                loadQuestion();
            }
        }
    }
}