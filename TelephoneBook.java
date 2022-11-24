import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TelephoneBook extends JFrame implements ActionListener {
    LinkedList<Person> linkedList;
    String operation;
    Container container;
    JPanel panel;
    JComboBox comboBox;
    JButton enterButton, exitButton;
    JTextArea textArea;
    JScrollPane scrollPane;

    public TelephoneBook()  {
        linkedList = new LinkedList<>();

        container = getContentPane();
        panel = new JPanel();

        String item [] ={"Add Person","Delete  Person","Update Telephone Number","Display all Records","Search Telephone Number","Sort all persons"};
        comboBox = new JComboBox(item);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);

        panel.add(comboBox);
        panel.add(enterButton);
        panel.add(exitButton);

        container.add(panel,"North");
        container.add(scrollPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Telephone Book");
        setLocation(100, 100);
        setSize(600, 600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(enterButton)){
            textArea.setText("");
            operation = comboBox.getSelectedItem().toString();

            if(operation.equals("Add Person"))
                addPerson();
            else if (operation.equals("Delete  Person"))
                deletePerson();
            else if (operation.equals("Update Telephone Number"))
                updateTelephoneNumber();
            else if (operation.equals("Display all Records"))
                displayAllRecord();
            else if (operation.equals("Search Telephone Number"))
                searchTelephoneNumber();
            else if (operation.equals("Sort all persons"))
                selectionSort(linkedList);
        }

        if (e.getSource().equals(exitButton)) System.exit(0);
    }

    public void addPerson(){
        String firstName = JOptionPane.showInputDialog(this,"Enter the first name:");
        String lastName = JOptionPane.showInputDialog(this,"Enter the last name:");
        String phoneNumber = JOptionPane.showInputDialog(this,"Enter the telephone number:");
        String city = JOptionPane.showInputDialog(this,"Enter the city :");
        String address = JOptionPane.showInputDialog(this,"Enter the address:");
        String gender = JOptionPane.showInputDialog(this,"Enter the gender:");
        String email = JOptionPane.showInputDialog(this,"Enter the email:");

        linkedList.add(new Person(firstName,lastName,phoneNumber,city,address,gender,email));

        JOptionPane.showMessageDialog(this,linkedList.getLast().getFirstName()+" "+linkedList.getLast().getLastName()+" has been added");
    }

    public void deletePerson(){
        String telephone = JOptionPane.showInputDialog(this,"Enter telephone number ");

        boolean flag=true;
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).getTelephoneNumber().equals(telephone)) {
                JOptionPane.showMessageDialog(this,linkedList.get(i).getFirstName()+" "+linkedList.get(i).getLastName()+" has been deleted");
                linkedList.remove(i);
                flag=false;
            }
        }

        if (flag) JOptionPane.showMessageDialog(this,telephone+" not found");
    }

    public void updateTelephoneNumber(){
        String name = JOptionPane.showInputDialog(this,"Enter the first name: ");

        boolean flag=true;
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).getFirstName().equals(name)) {
                String n = JOptionPane.showInputDialog(this,"Enter new telephone number ");
                linkedList.get(i).setTelephoneNumber(n);
                JOptionPane.showMessageDialog(this,"telephone number has changed to :"+"\n"+linkedList.get(i).getTelephoneNumber());
                flag=false;
            }
        }

        if (flag) JOptionPane.showMessageDialog(this,name+" not found");
    }

    public void displayAllRecord(){
        for (int i = 0; i< linkedList.size(); i++){
            textArea.append("Person "+(i+1)+":"+"\n");
            textArea.append(linkedList.get(i).toString()+"\n");
        }
    }

    public void searchTelephoneNumber(){
        String firstName = JOptionPane.showInputDialog(this,"Enter the first name: ");

        boolean flag=true;
        for (int i = 0; i < linkedList.size(); i++) {
            if (linkedList.get(i).getFirstName().equals(firstName)) {
                JOptionPane.showMessageDialog(this,"Telephone Number is : \n"+linkedList.get(i).getTelephoneNumber());
                flag=false;
            }
        }

        if (flag) JOptionPane.showMessageDialog(this, firstName +" not found");
    }



    public void selectionSort (LinkedList <Person> l) {
        int n = l.size();
        int mini, i, j;

        for (i = 0; i < n - 1; i++) {
            mini = i;
            for (j = i + 1; j < n; j++) {
                String first = (linkedList.get(mini).getFirstName()+" "+linkedList.get(mini).getLastName());
                String second = (linkedList.get(j).getFirstName()+" "+linkedList.get(j).getLastName());
                if (first.compareTo(second) > 0) mini = j;
            }

            if (i != mini) {
                linkedList.add(i, l.get(mini));
                linkedList.remove(mini + 1);
            }
        }

        JOptionPane.showMessageDialog(this, "Sorted!!");
    }

    public static void main(String[] args) {
        new TelephoneBook();
    }
}
