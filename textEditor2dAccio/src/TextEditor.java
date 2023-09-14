import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JTextArea textArea;
    JMenu file, edit;
    JMenuItem newFile, openFile, saveFile;
    JMenuItem copy, cut, paste, selectAll, closeAll;

    TextEditor() {
        frame = new JFrame();

        menuBar = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        textArea = new JTextArea();

        menuBar.add(file);
        menuBar.add(edit);
        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        menuBar.setBackground(Color.YELLOW);


        //adding file menu items
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");
        //add action lister for the file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //adding edit menu items
        copy = new JMenuItem("Copy");
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        closeAll = new JMenuItem("Close All");
        //add actions listers to the edit menu items
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        closeAll.addActionListener(this);

        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(closeAll);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea, BorderLayout.CENTER);

        JScrollPane scroolPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scroolPane);
        frame.add(panel);
        frame.setBounds(100, 100, 800, 500);

        frame.setVisible(true);
    }
         @Override
        public void actionPerformed( ActionEvent actionEvent ){
             if(actionEvent.getSource() == cut){
                textArea.cut();
             }
             if(actionEvent.getSource() == copy){
                 textArea.copy();
             }
             if(actionEvent.getSource() == paste){
                 textArea.paste();
             }
             if(actionEvent.getSource() == selectAll){
                 textArea.selectAll();
             }
             if(actionEvent.getSource() == closeAll){
                 System.exit(0);
             }
             if(actionEvent.getSource()==openFile){
                 //Initialized a file chooser
                 JFileChooser fileChooser = new JFileChooser("D:");
                 //Getting chosen option in file chooser
                 int chooseOption = fileChooser.showOpenDialog(null);
                 //If chosen option is the approve option
                 if(chooseOption==JFileChooser.APPROVE_OPTION){
                     //Getting selected file from file chooser
                     File file = fileChooser.getSelectedFile();
                     //Getting path of the selected file
                     String filePath = file.getPath();
                     try{
                         //Initialize file reader
                         FileReader fileReader = new FileReader(filePath);
                         //Initialize buffered reader
                         BufferedReader bufferedReader = new BufferedReader(fileReader);
                         //Intermediate for current line; output for complete content of file
                         String intermediate = "", output = "";
                         //Read line by line
                         while((intermediate = bufferedReader.readLine())!=null){
                             output+=intermediate+"\n";
                         }
                         bufferedReader.close();
                         //set text area with the content of the file
                         textArea.setText(output);
                     }
                     catch (Exception exception){
                         exception.printStackTrace();
                     }
                 }
             }
             //If saveFile menu item is clicked
             if(actionEvent.getSource()==saveFile){
                 //Initialize File chooser
                 JFileChooser fileChooser = new JFileChooser("d:");
                 //Get chosen option from file chooser
                 int chooseOption = fileChooser.showSaveDialog(null);
                 //If chosen option is the approve option
                 if(chooseOption==JFileChooser.APPROVE_OPTION){
                     //Create a new file with directory's path
                     File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                     try{
                         BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                         textArea.write(bufferedWriter);
                         bufferedWriter.close();
                     }catch (Exception exception){
                         exception.printStackTrace();
                     }
                 }
             }
             if(actionEvent.getSource() == newFile){
                 TextEditor newWindow = new TextEditor();
             }
        }


    public static void main(String[] args){

        TextEditor textEditor = new TextEditor();
    }
}