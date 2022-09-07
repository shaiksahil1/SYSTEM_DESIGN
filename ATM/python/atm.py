class user_account:
    def __init__(self,name,account_number,phone_number,intial_amount):
        self.name = name
        self.account_number = account_number
        self.phone_number = phone_number
        self.balance =  intial_amount
    def withdraw(self,amount):
        if(amount<=self.balance):
            self.balance -= amount
            return 1
        else:
            return 0
    def deposit(self,amount):
        self.balance += amount
    def check_balance(self):
        return self.balance

#Maintains a global dictionary of the the account holders corresponding to their user_account object
Accounts = {}

#maintains a global dictionary of the account_number corresponding to their password
accountno_password = {}

#maintains the account number of the currently logged in user
global_account_number = ""

def create_account(username,phone_number,password,intial_amount,account_number):
    new_user = user_account(username, account_number, phone_number, intial_amount)
    #Storing the new_user object into dictionary using account_no as a key 
    Accounts[account_number] = new_user
    #storing the password into dictionary using account_no as a password
    accountno_password[account_number] = password
    return account_number
    
def login(account_number,password):
    #verfying the user account_number againt the password using accountno_password dictionary
    if(accountno_password[account_number]==password):
        global_account_number = account_number
        return True

def logout():
    #making the global_account_number to null indicates no user has been logged in
    global_account_number = ""

def change_password(account_number,password):
    #verifying account_number against the password
    if(accountno_password[account_number]==password):
        new_password = input("enter new password: ")
        accountno_password[account_number] = new_password
        print("********************************************************\n")
        print("password changed successfully\n")
        print("********************************************************\n")
    else:
        print("Invalid password")

#for filling sample data
def fill_data():
    name = ["vamsi","saketh","bindu","uday"]
    phone_numbers = ["123","124","12345","2345"]
    account_numbers = ["1","2","3","4"]
    passwords = ["a","b","c","d"]
    intial_amounts = [120,150,100,140]
    for i in range(4):
        create_account(name[i], phone_numbers[i], passwords[i], intial_amounts[i], account_numbers[i])

    
fill_data()
while(True):
    print("********************************************************\n")
    print("Welcome to GVPAI Bank\n")
    print("********************************************************\n")
    print("1.Login\n2.Exit")
    choice = int(input())
    if(choice==1):
        account_number = input("Enter Account number: ")
        password = input("Enter password: ")
        #checking whether the entered account_number is valid or not
        if(account_number in Accounts.keys()):
            #checking whether the entered password is valid or not
            if(login(account_number, password)):
                global_account_number = account_number
                logged_in_user_obj = Accounts[global_account_number]
                print("********************************************************\n")
                print("welcome back "+logged_in_user_obj.name+"\n")
                print("********************************************************\n")
                innerchoice = True
                while(innerchoice):
                    print("1.check balance\n2.Deposit\n3.withdraw\n4.change password\n5.Logout\n")
                    print("********************************************************\n")
                    user_choice = int(input("Enter choice: "))
                    if(user_choice==1):
                        balance = logged_in_user_obj.check_balance()
                        print("********************************************************\n")
                        print("Balance = ",balance,"\n")
                        print("********************************************************\n")
                    elif(user_choice==2):
                        deposit_amount = int(input("Enter deposit amount: "))
                        logged_in_user_obj.deposit(deposit_amount)
                        print("********************************************************\n")
                        print("Balance updated\n")
                        print("********************************************************\n")
                    elif(user_choice == 3):
                        withdraw_amount = int(input("Enter withdraw amount: "))
                        status = logged_in_user_obj.withdraw(withdraw_amount)
                        if(status==1):
                            print("********************************************************\n")
                            print("Balance updated\n")
                            print("********************************************************\n")
                        else:
                            print("********************************************************\n")
                            print("cannot perform this operation\n")
                            print("********************************************************\n")
                    elif(user_choice==4):
                        password = input("Enter current password: ")
                        change_password(global_account_number, password)
                    elif(user_choice==5):
                        logout()
                        break
                    else:
                        print("********************************************************\n")
                        print("Invalid Choice\n")
                        print("********************************************************\n")
            else:
                print("********************************************************\n")
                print("Invalid password\n")
                print("********************************************************\n")
        else:
            print("********************************************************\n")
            print("Invalid Account number\n")
            print("********************************************************\n")
    else:
        break
        
    


    
