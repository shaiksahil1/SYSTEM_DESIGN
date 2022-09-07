public class User_account {
    String name;
    String phone_number;
    String account_number;
    int balance;
    User_account(String name,String phone_number,String account_number,int balance ){
        this.name = name;
        this.phone_number = phone_number;
        this.account_number = account_number;
        this.balance = balance;
    }
     int check_balance(){
        return this.balance;
     }

     void deposit(int amount){
        this.balance += amount;
     }

     int withdraw(int amount){
        if(amount<=this.balance){
            this.balance -= amount;
            return 1;
        }
        else{
            return 0;
        }
     }
}