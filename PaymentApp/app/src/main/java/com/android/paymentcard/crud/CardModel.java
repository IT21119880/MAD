package com.android.paymentcard.crud;

public class CardModel
{
	private String key;
	private String CardNumber;
	private String nameOnCard;
	private String expireDate;
	private String ccv;
	
	public CardModel() {
		
	}
	public void setKey(String key){
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
	
	public void setCardNumber(String cardNumber){
		this.CardNumber = cardNumber;
	}
	
	public String getCardNumber(){
		return CardNumber;
	}
	
	public void setNameOnCard(String nameOnCard){
		this.nameOnCard = nameOnCard;
	}
	
	public String getNameOnCard(){
		return nameOnCard;
	}
	
	public void setExpireDate(String expireDate){
		this.expireDate = expireDate;
	}
	
	public String getExpireDate(){
		return expireDate;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public CardModel(String CardNumber, String nameOnCard, String expireDate, String ccv){
		this.CardNumber = CardNumber;
		this.nameOnCard = nameOnCard;
		this.expireDate = expireDate;
		this.ccv = ccv;
	}
}
