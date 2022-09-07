#include<iostream>
#include<string>
#include<unordered_map>
#include<vector>

typedef long long int ll;

using namespace std;

// class for user account
class userAccount {
    private:
        string name;
        string account_number;
        string phone_number;
        ll balance;
    public:
        userAccount(string name, string account_number, string phone_number, ll initial_amount);
        userAccount() = default;

        bool withdraw(ll amount);
        void deposit(ll amount);
        ll check_balance();
        string get_name();
};

// constructor
userAccount::userAccount(string name, string account_number, string password, ll balance) { 
    this->name = name;
    this->account_number = account_number;
    this->phone_number = phone_number;
    this->balance = balance;    
}

// withdraw amount with sufficient balance check
bool userAccount::withdraw(ll amount) {
    if(amount <= balance) {
        balance = balance - amount;
        return true;
    } else {
        return false;
    }
}

// deposit amount
void userAccount::deposit(ll amount) {
    balance = balance + amount;
}

// check available balance
ll userAccount::check_balance() {
    return balance;
}

string userAccount::get_name() {
    return name;
}

// map to maintain account objects corresponding to account number
unordered_map<string, userAccount> accounts;
// map to maintain account password correspoding to account number
unordered_map<string, string> account_pwds;

// login with validation
bool login(string account_number, string password) {
    if(account_pwds[account_number] == password) {
        return true;
    } 
    return false;
}

// create account with relevant details
void create_acount(string name, string phone_number, string account_number, string password, ll balance) {
    userAccount account(name, phone_number, account_number, balance);
    accounts[account_number] = account;
    account_pwds[account_number] = password;
}

void fill_details() {
    vector<string> names = {"vamsi", "saketh", "bindu", "uday"};
    vector<string> phone_numbers = {"123", "124", "12345", "2345"};
    vector<string> account_numbers = {"1", "2", "3", "4"};
    vector<string> passwords = {"a", "b", "c", "d"};
    vector<int> balances = {120, 130, 140, 150};
    
    for(int i = 0; i < names.size(); ++i) {
        create_acount(names[i], phone_numbers[i], account_numbers[i], passwords[i], balances[i]);
    }
}

int main() {
    // global account number to maintain logged in user
    string global_account_number = "";
    fill_details();
    int choice;

    do {
        cout << "\nWelcome to GVPAI Bank\n";
        cout << "1. Login\n2. Exit\nChoose an option: ";
        cin >> choice;
        switch(choice) {
            case 1: {
                string current_account, password;
                cout << "Enter your account number: ";  
                cin >> current_account;
                cout << "Enter your password: ";
                cin >> password;

                if(accounts.find(current_account) != accounts.end()) {
                    if(login(current_account, password)) {
                        global_account_number = current_account;
                        userAccount *curr_account_obj = &accounts[global_account_number];
                        cout << "\nWelcome back, " << curr_account_obj->get_name() << "\n";

                        while(true) {
                            cout << "\n1. Withdraw\n2. Deposit\n3. Check Balance\n4. Change Password\n5. Logout\nEnter your choice: ";
                            int inner_choice;
                            cin >> inner_choice;

                            switch(inner_choice) {
                                case 1: {
                                    ll amount;
                                    cout << "Enter an amount to withdraw: ";
                                    cin >> amount;
                                    bool status = curr_account_obj->withdraw(amount);
                                    if(status) {
                                        cout << "\nAmount withdrawn. Balance updated.\n";
                                    } else {
                                        cout << "\nInsufficient Balance.\n";
                                    }
                                    break;
                                }
                                case 2: {
                                    ll amount;
                                    cout << "Enter the amount you want to deposit: ";
                                    cin >> amount;
                                    curr_account_obj->deposit(amount);
                                    cout << "\nAmount deposited.\n";
                                    break;
                                }
                                case 3: {
                                    cout << "\nCurrent Balance: " << curr_account_obj->check_balance() << "\n";
                                    break;
                                }
                                case 4: {
                                    cout << "\nEnter the new password: ";
                                    string new_password;
                                    cin >> new_password;
                                    account_pwds[current_account] = new_password;
                                    cout << "\nPassword updated successfully.\n";
                                    global_account_number = "";
                                    break;
                                }
                                case 5: {
                                    global_account_number = "";
                                    break;
                                }
                                default: {
                                    cout << "\nEnter a valid choice.\n";
                                }
                            }
                            if(global_account_number == "") {
                                break;
                            }
                        }
                    } else {
                        cout << "\nInvalid Username or Password.\n";
                    }
                } else {
                    cout << "\nInvalid Username or Password.\n";
                }
                break;
            }
            case 2: {
                break;
            }
            default: {
                cout << "\nEnter a valid choice.\n";
            }
        }
    }  while(choice != 2);
}