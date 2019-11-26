import java.util.Scanner;

/**
 * @author Henry Herrera 
 * */

public class DeclineCurves {

	static float annualDeclineRatePercentage; //Known value
	static float annualDeclineRateDecimal; //Known value
	static float exponentialDeclineRate = (float) Math.pow((1 - annualDeclineRatePercentage), 12);
	static float monthlyDeclineRate;
	static float qoi; // Known value
	static float qoL; // Known value
	static float qo;
	static int year = 0;
	static float deltaNp;
	static float np;
	

	enum declineType {
		exponential, harmonic, hyperbolic
	}
	
	enum annualDeclineRateType{
		percentage, decimal
	}

	static float calculateDeclineRate() {
		if(annualDeclineRateType.percentage != null) {
			monthlyDeclineRate = (float) Math.log((float) Math.pow((1 - annualDeclineRatePercentage/100), 12));
		}else if(annualDeclineRateType.decimal != null) {
			monthlyDeclineRate = (float) Math.log(1-annualDeclineRateDecimal);
		}
		return monthlyDeclineRate;
	}

	static void productionPrediction() {
		
		if (declineType.exponential != null) {
			exponentialDecline();
		} else if (declineType.harmonic != null) {
			harmonicDecline();
		} else if (declineType.hyperbolic != null) {
			hyperbolicDecline();
		}
	}
	
	/**
	 * Method to calculate exponential decline curves with fixed parameters
	 * */

	static void exponentialDecline() {
		//calculateDeclineRate();
		exponentialDeclineRate = 0.7847f;
		monthlyDeclineRate = 0.24245f;
		qoi= 115;
		qoL = 6.3f;
		np = 0;
		float tempQo = 0;
		
		System.out.println("Year: " + year + " Np: " + np + " Qo: " + qo + " deltaNp: " + deltaNp);
		
		year++;
		
		qo = exponentialDeclineRate * qoi;
		deltaNp = ((qoi - qo)/ monthlyDeclineRate)*365;
		np = np + deltaNp;
		System.out.println("Year: " + year + " Np: " + np + " Qo: " + qo + " deltaNp: " + deltaNp);
		tempQo = qo;
		
		//year++;
		
		while(qo > qoL) {	
			qo = exponentialDeclineRate * tempQo;
			deltaNp = ((tempQo - qo)/ monthlyDeclineRate)*365;
			np = np + deltaNp;
			year++;
			// These values need to be saved
			System.out.println("Year: " + year + " Np: " + np + " Qo: " + qo + " deltaNp: " + deltaNp);
			tempQo = qo;
		}
		
	}
	
	/**
	 * Method to calculate exponential decline curves with parameters introduced by the user
	 * */
	
	static void exponentialDecline(float declineRate, float qoi, float qoL) {
		//calculateDeclineRate();
		
		exponentialDeclineRate = (float) Math.pow(1-((float) declineRate/100), 12);
		monthlyDeclineRate = (float) Math.log(exponentialDeclineRate)*(-1);
		
		System.out.println("De: " + exponentialDeclineRate + ", Dn: " +  monthlyDeclineRate + ", Qoi: " 
		+ qoi +  ", QL:" + qoL);
//		qoi= 115;
//		qoL = 6.3f;
		np = 0;
		float tempQo = 0;
		
		System.out.println("Year: " + year + " Np: " + np + " Qo: " + qoi + " deltaNp: " + deltaNp);
		
		year++;
		
		qo = exponentialDeclineRate * qoi;
		deltaNp = ((qoi - qo)/ monthlyDeclineRate)*365;
		np = np + deltaNp;
		System.out.println("Year: " + year + " Np: " + np + " Qo: " + qo + " deltaNp: " + deltaNp);
		tempQo = qo;
		
		while(qo > qoL) {	
			qo = exponentialDeclineRate * tempQo;
			deltaNp = ((tempQo - qo)/ monthlyDeclineRate)*365;
			np = np + deltaNp;
			year++;
			// These values need to be saved
			System.out.println("Year: " + year + " Np: " + np + " Qo: " + qo + " deltaNp: " + deltaNp);
			tempQo = qo;
		}
		
	}
	
	/**
	 * Method to calculate harmomic decline curves with fixed parameters
	 * */

	static void harmonicDecline() {
		//calculateDeclineRate();
		monthlyDeclineRate = 0.24245f;
		qoi= 115;
		qoL = 6.3f;
		deltaNp = 0;
		float tempNp = 0;
		
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		
		year++;
		
		qo = qoi / (1 + monthlyDeclineRate*year);
		np = (float) (((qoi / monthlyDeclineRate) * Math.log(qoi/qo))* 365);
		deltaNp = np - deltaNp;
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		tempNp = np;
		year++;
		
		while(qo > qoL) {			
			qo = qoi / (1 + monthlyDeclineRate*year);
			np = (float) (((qoi / monthlyDeclineRate) * Math.log(qoi/qo))* 365);
			deltaNp = np - tempNp;
			tempNp = np;
			System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
			year++;
			
			// These values need to be saved
			
		}
		
	}
	
	/**
	 * Method to calculate harmonic decline curves with parameters provided by the user
	 * */
	
	static void harmonicDecline(float declineRate, float qoi, float qoL) {
		//calculateDeclineRate();
		exponentialDeclineRate = (float) Math.pow(1-((float) declineRate/100), 12);
		monthlyDeclineRate = (float) Math.log(exponentialDeclineRate)*(-1);
		deltaNp = 0;
		float tempNp = 0;
		
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		
		year++;
		
		qo = qoi / (1 + monthlyDeclineRate*year);
		np = (float) (((qoi / monthlyDeclineRate) * Math.log(qoi/qo))* 365);
		deltaNp = np - deltaNp;
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		tempNp = np;
		year++;
		
		while(qo > qoL) {			
			qo = qoi / (1 + monthlyDeclineRate*year);
			np = (float) (((qoi / monthlyDeclineRate) * Math.log(qoi/qo))* 365);
			deltaNp = np - tempNp;
			tempNp = np;
			System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
			year++;
			
			// These values need to be saved
			
		}
		
	}
	
	/**
	 * Method to calculate hyperbolic decline curves with fixed parameters
	 * */

	static void hyperbolicDecline() {
		//calculateDeclineRate();
		final float n = 0.5f;
		monthlyDeclineRate = 0.24245f;
		qoi= 115;
		qoL = 6.3f;
		deltaNp = 0;
		float tempNp = 0;
		
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		
		year++;
		qo = (float) (qoi * Math.pow((1 + n * monthlyDeclineRate*year), -1/n));
		np = (float) (((Math.pow(qoi, n) / ((1 - n) * monthlyDeclineRate))) * ((Math.pow(qoi, n) - Math.pow(qo, n))) * 365);
		deltaNp = np - deltaNp;
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		tempNp = np;
		year++;
		
		while(qo > qoL) {
			qo = (float) (qoi * Math.pow((1 + n * monthlyDeclineRate*year), -1/n));
			np = (float) (((Math.pow(qoi, n) / ((1 - n) * monthlyDeclineRate))) * ((Math.pow(qoi, n) - Math.pow(qo, n))) * 365);
			deltaNp = np - tempNp;
			System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
			tempNp = np;
			year++;
			// These values need to be saved
		}
	}
	
	/**
	 * Method to calculate hyperbolic decline curves with parameters provided by the user
	 * */
	
	static void hyperbolicDecline(float declineRate, float qoi, float qoL) {
		//calculateDeclineRate();
		final float n = 0.5f;
		exponentialDeclineRate = (float) Math.pow(1-((float) declineRate/100), 12);
		monthlyDeclineRate = (float) Math.log(exponentialDeclineRate)*(-1);
		
		deltaNp = 0;
		float tempNp = 0;
		
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		
		year++;
		qo = (float) (qoi * Math.pow((1 + n * monthlyDeclineRate*year), -1/n));
		np = (float) (((Math.pow(qoi, n) / ((1 - n) * monthlyDeclineRate))) * ((Math.pow(qoi, n) - Math.pow(qo, n))) * 365);
		deltaNp = np - deltaNp;
		System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
		tempNp = np;
		year++;
		
		while(qo > qoL) {
			qo = (float) (qoi * Math.pow((1 + n * monthlyDeclineRate*year), -1/n));
			np = (float) (((Math.pow(qoi, n) / ((1 - n) * monthlyDeclineRate))) * ((Math.pow(qoi, n) - Math.pow(qo, n))) * 365);
			deltaNp = np - tempNp;
			System.out.println("Year: " + year + " Qo: " + qo + " Np: " + np +  " deltaNp: " + deltaNp);
			tempNp = np;
			year++;
			// These values need to be saved
		}
	}

	public static void main(String[] args) {
		//Scanner to insert data from users
		Scanner declineType = new Scanner(System.in);
		Scanner declineRateInput = new Scanner(System.in);
		Scanner initialFlowRateInput = new Scanner(System.in);
		Scanner limitFlowRateInput = new Scanner(System.in);
		
		//Selection of the decline curve type
		System.out.println("What decline curve type you want to calculate? exponential, harmonic or hyperbolic?");
		String declineTypeSelection = declineType.nextLine().toLowerCase();
		
		if(declineTypeSelection.equals("exponential")) {
			//Decline rate in %
			System.out.println("Enter the decline rate(%)");
			float declineRate = declineRateInput.nextFloat();
			//Initial Flow Rate Qoi
			System.out.println("Enter the initial Flow Rate (Qoi) in BOPD:");
			float initialFlowRate = initialFlowRateInput.nextFloat();
			//Limit Flow Rate QoL
			System.out.println("Enter the limit Flow Rate (QoL) in BOPD:");
			float limitFlowRate = limitFlowRateInput.nextFloat();
			exponentialDecline(declineRate, initialFlowRate, limitFlowRate);
		} else if(declineTypeSelection.equals("harmonic")) {
			//Decline rate in %
			System.out.println("Enter the decline rate(%)");
			float declineRate = declineRateInput.nextFloat();
			//Initial Flow Rate Qoi
			System.out.println("Enter the initial Flow Rate (Qoi) in BOPD:");
			float initialFlowRate = initialFlowRateInput.nextFloat();
			//Limit Flow Rate QoL
			System.out.println("Enter the limit Flow Rate (QL) in BOPD:");
			float limitFlowRate = limitFlowRateInput.nextFloat();
			//Calling the method after getting the parameters
			harmonicDecline(declineRate, initialFlowRate, limitFlowRate);
		} else if(declineTypeSelection.equals("hyperbolic")) {
			//Decline rate in %
			System.out.println("Enter the decline rate(%)");
			float declineRate = declineRateInput.nextFloat();
			//Initial Flow Rate Qoi
			System.out.println("Enter the initial Flow Rate (Qoi) in BOPD:");
			float initialFlowRate = initialFlowRateInput.nextFloat();
			//Limit Flow Rate QoL
			System.out.println("Enter the limit Flow Rate (QL) in BOPD:");
			float limitFlowRate = limitFlowRateInput.nextFloat();
			//Calling the method after getting the parameters
			hyperbolicDecline(declineRate, initialFlowRate, limitFlowRate);
		} else {
			System.out.println("You need to choose a decline curve that exists");
		}
		
	}

}