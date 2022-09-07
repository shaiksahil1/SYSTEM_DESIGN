import java.util.*;
public class Atm {
    static Scanner sc = new Scanner(System.in);
    static HashMap<String,User_account> Accounts = new HashMap<>();
    static HashMap<String,String> acc_pass = new HashMap<>();
    static String global_user_acc_no = "";

    public static void create_account(String name,String phone_number,String account_number,String password,int intial_amount){
        User_account new_user = new User_account(name,phone_number,account_number,intial_amount);
        Accounts.put(account_number,new_user);
        acc_pass.put(account_number,password);
    }

    public static int login(String account_number,String password){
        if(acc_pass.get(account_number).equals(password)){
            global_user_acc_no = account_number;
            return 1;
        }
        return 0;
    }

    public static void logout(){
        global_user_acc_no = "";
    }

    public static void change_password(String account_number,String password){
        if(acc_pass.get(account_number).equals(password)){
            System.out.println("Enter new password");
            String new_password = sc.next();
            acc_pass.put(account_number,new_password);
            System.out.println("*******************************\n");
            System.out.println("Password succesfully changed\n");
            System.out.println("*******************************\n");
        }
        else{
            System.out.println("*******************************\n");
            System.out.println("Invalid details\n");
            System.out.println("*******************************\n");
        }
    }

    public static void fill_data(){
        ArrayList<String> names = new ArrayList<String>(Arrays.asList("vamsi", "saketh", "bindu","uday"));
        ArrayList<String> phone_numbers = new ArrayList<String>(Arrays.asList("123", "124", "12345","2345"));
        ArrayList<String> account_numbers = new ArrayList<String>(Arrays.asList("1", "2", "3","4"));
        ArrayList<String> passwords = new ArrayList<String>(Arrays.asList("a", "b", "c","d"));
        ArrayList<Integer> inital_amounts = new ArrayList<Integer>(Arrays.asList(120,150,100,140));
        for(int i=0;i<4;i++){
            create_account(names.get(i),phone_numbers.get(i),account_numbers.get(i),passwords.get(i),inital_amounts.get(i));
        }
    }

    public static void main(String s []){
        System.out.println("*******************************\n");
        System.out.println("Welcome to GVPAI Bank\n");
        System.out.println("*******************************\n");
        fill_data();
        boolean flag = true;
        while(flag){
            System.out.println("1.Login\n2.Exit");
            System.out.println("Enter choice");
            int choice = sc.nextInt();
            switch (choice){
                case 1: System.out.println("Enter Account number: ");
                        String account_number= sc.next();
                        System.out.println("Enter password: ");
                        String password = sc.next();
                        if(Accounts.containsKey(account_number)) {
                            if (login(account_number, password) == 1) {
                                User_account current_user = Accounts.get(account_number);
                                System.out.println("*******************************\n");
                                System.out.println("Welcome back "+current_user.name+"\n");
                                System.out.println("*******************************\n");
                                boolean innerflag = true;
                                while(innerflag) {
                                    System.out.println("1.Check Balance\n2.Deposit\n3.Withdrawal\n4.changepassword\n5.Logout");
                                    System.out.println("Enter choice: ");
                                    int innerchoice = sc.nextInt();
                                    switch (innerchoice) {
                                        case 1:
                                            int balance = current_user.check_balance();
                                            System.out.println("*******************************\n");
                                            System.out.println("Balance = " + balance + "\n");
                                            System.out.println("*******************************\n");
                                            break;
                                        case 2:
                                            System.out.println("Enter Deposit amount : ");
                                            int deposit_amount = sc.nextInt();
                                            current_user.deposit(deposit_amount);
                                            System.out.println("*******************************\n");
                                            System.out.println("Balance updated\n");
                                            System.out.println("*******************************\n");
                                            break;
                                        case 3:
                                            System.out.println("Enter withdraw amount : ");
                                            int withdraw_amount = sc.nextInt();
                                            int status = current_user.withdraw(withdraw_amount);
                                            if (status == 1) {
                                                System.out.println("*******************************\n");
                                                System.out.println("Balance updated\n");
                                                System.out.println("*******************************\n");
                                            }
                                            else{
                                                System.out.println("*******************************\n");
                                                System.out.println("Insufficient Balance\n");
                                                System.out.println("*******************************\n");
                                            }
                                            break;
                                        case 4:
                                            System.out.println("Enter current password : ");
                                            String curr_password = sc.next();
                                            change_password(global_user_acc_no, curr_password);
                                            break;
                                        case 5:
                                            logout();
                                            innerflag = false;
                                            break;
                                        default:
                                            System.out.println("*******************************\n");
                                            System.out.println("Invalid choice\n");
                                            System.out.println("*******************************\n");
                                    }
                                }
                            } else {
                                System.out.println("*******************************\n");
                                System.out.println("Invalid password\n");
                                System.out.println("*******************************\n");
                            }
                        }
                        else{
                            System.out.println("*******************************\n");
                            System.out.println("Invalid Account number\n");
                            System.out.println("*******************************\n");
                        }
                        break;
                case 2: flag = false;
                        break;
                default:
                    System.out.println("*******************************\n");
                    System.out.println("Invalid choice\n");
                    System.out.println("*******************************\n");
            }
        }
    }
}


