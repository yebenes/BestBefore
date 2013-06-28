import java.text.ParseException
import java.text.SimpleDateFormat

class BestBeforeException extends Exception{}

class BestBefore {

	def dateParser = new SimpleDateFormat("dd/MM/yy")

	public BestBefore() {
		dateParser.lenient = false
	}

	public String getBestBeforeDate(String date) {
		try {
			return bestDate(date)
		} catch(Exception e) {
			throw new BestBeforeException()
		} 
	}

	private String bestDate(def date) {
		def dateSplit = date.split("/")
		def (a, b, c) = dateSplit.sort().collect() { it as int }
		for(currentDate in [[a, b, c], [b, a, c], [c, a, b]]) {
			def (year, month, day) = currentDate
			if(year < 2000) { year = year + 2000 }
			try {
				return checkDate(day, month, year)
			} catch(ParseException e) {}	
		}
		throw new BestBeforeException()
	}
	
	private String checkDate(def day, month, year) {
		String date = day + "/" + month + "/" + year 
		dateParser.parse(date)
		return date
	}
}
