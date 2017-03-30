package com.xyz.vdms;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.xyz.vdms.controller.manager.Facade;

public class Tester {
	
	public static void main(String[] args) throws Exception{
		
		boolean valid = true;
		boolean validMoney = true;
		double price = 0.0;
		double balance = 0.0;
		double bal = 0.0;
		int moneyID = 0;
		int id = 0, qty = 0;
		int t = 0;
		
		try (Facade facade = new Facade()){
			
			System.out.println("\n\n'##::::'##:'########:'##::: ##:'########::'####:'##::: ##::'######:::'##::::'##::::'###:::::'######::'##::::'##:'####:'##::: ##:'########:");
			System.out.println(" ##:::: ##: ##.....:: ###:: ##: ##.... ##:. ##:: ###:: ##:'##... ##:: ###::'###:::'## ##:::'##... ##: ##:::: ##:. ##:: ###:: ##: ##.....::");
			System.out.println(" ##:::: ##: ##::::::: ####: ##: ##:::: ##:: ##:: ####: ##: ##:::..::: ####'####::'##:. ##:: ##:::..:: ##:::: ##:: ##:: ####: ##: ##:::::::");
			System.out.println(" ##:::: ##: ######::: ## ## ##: ##:::: ##:: ##:: ## ## ##: ##::'####: ## ### ##:'##:::. ##: ##::::::: #########:: ##:: ## ## ##: ######:::");
			System.out.println(". ##:: ##:: ##...:::: ##. ####: ##:::: ##:: ##:: ##. ####: ##::: ##:: ##. #: ##: #########: ##::::::: ##.... ##:: ##:: ##. ####: ##...::::");
			System.out.println(":. ## ##::: ##::::::: ##:. ###: ##:::: ##:: ##:: ##:. ###: ##::: ##:: ##:.:: ##: ##.... ##: ##::: ##: ##:::: ##:: ##:: ##:. ###: ##:::::::");
			System.out.println("::. ###:::: ########: ##::. ##: ########::'####: ##::. ##:. ######::: ##:::: ##: ##:::: ##:. ######:: ##:::: ##:'####: ##::. ##: ########:");
			System.out.println(":::...:::::........::..::::..::........:::....::..::::..:::......::::..:::::..::..:::::..:::......:::..:::::..::....::..::::..::........::");
		
			System.out.println("");
			facade.displayProduct();
			
			while(valid){
				
				
				
				try{
					System.out.print("Please select a product: ");
					Scanner scan = new Scanner (System.in);
					id = scan.nextInt();
					valid = facade.checkProduct(id, valid);
				}
				catch (InputMismatchException e){
					System.out.println("Invalid number, please try again.");
				}			
			}
			valid = true;
			
			while(valid){
				try{
					System.out.print("Please enter the desired quantity: ");
					Scanner scan = new Scanner (System.in);
					qty = scan.nextInt();
					if (qty > facade.checkProductQuantity(id)){
						System.out.println("Insufficient quantity, please try again");
					}
					else{
						valid = false;
					}
				}
				catch (InputMismatchException e){
					System.out.println("Invalid number, please try again");
				}
			}
			price = qty * facade.getPrice(id);
			price = roundTwoDecimals(price);
			System.out.println("Please pay RM " + roundTwoDecimals(price) + "0");
			System.out.println();
			facade.displayMoney();
			valid = true;
			while(valid){
				try{
					System.out.print("Please enter the money: ");
					Scanner scan = new Scanner(System.in);
					moneyID = scan.nextInt();
					validMoney = facade.checkMoneyID(moneyID, valid);
					
					if(validMoney){
						facade.addQuantity(moneyID);
					
						if(roundTwoDecimals(facade.getValue(moneyID)) - price == 0){
							System.out.println("Sales successfully made, dispensing product...");
							facade.updateProductQuantity(id, qty);
							facade.createSale(id,qty,price);
							System.out.println("Thank you very much, please come again.");
							valid = false;
						}
						else if(roundTwoDecimals(facade.getValue(moneyID))- price > 0){
							balance = facade.getValue(moneyID) - price;
							balance = roundTwoDecimals(balance);
							//System.out.println("Sales successfully made, dispensing product...");
							
							//System.out.println("Please wait for your balance RM " + roundTwoDecimals(balance));
							if (facade.sufficientAmount(balance)){
								System.out.println("Sales successfully made, dispensing product...");
								facade.updateProductQuantity(id,qty);
								facade.createSale(id,qty,price);
								for(t = 6; t > 0 ; t--){
									bal = balance - facade.getValue(t);
									bal = roundTwoDecimals(bal);
									if (bal >= 0){
										if(facade.checkQuantity(t) > 0){
											balance = balance - facade.getValue(t);
											facade.minusQuantity(t);
											System.out.println("Dispensing RM " + facade.getValue(t) + "0");
											t+=1;
										}
									}
								}
							}
							
							
							else{
								System.out.println("Sorry insufficient money for the remaining RM " + roundTwoDecimals(balance) + "0");
								System.out.println("Please collect your money back");
								System.out.println("Dispensing RM " + facade.getValue(moneyID) + "0");
								facade.minusQuantity(moneyID);
							}
							facade.close();
							System.out.println("Thank you very much, please come again.");
							valid = false;
						}
						else{
							price = price - facade.getValue(moneyID);
							System.out.println("Please enter remaining RM " + roundTwoDecimals(price) + "0");
							facade.displayMoney();
						}
					}
					else{
						facade.displayMoney();
					}
				}
				catch (InputMismatchException e){
					System.out.println("Invalid number, please try again.");
				}
			}
			facade.close();
		}
	}
	private static double roundTwoDecimals(double d) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(d));
	}
}
