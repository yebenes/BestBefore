import groovy.util.GroovyTestCase

class BestBeforeTests extends GroovyTestCase {

	private BestBefore bestBefore = new BestBefore();
	
	void testEmptyDate() {
		try {
			bestBefore.getBestBeforeDate("");
			fail()
		} catch(Exception e) {
			assert e in BestBeforeException
		}
	}
	
	void testCorrectDate() {
		String bestDate = bestBefore.getBestBeforeDate("01/01/2001");
		assert bestDate == "1/1/2001"
	}

	void testCorrectDate2() {
		String bestDate = bestBefore.getBestBeforeDate("12/31/2999");
		assert bestDate == "31/12/2999"
	}
	
	void testCorrectDate3() {
		String bestDate = bestBefore.getBestBeforeDate("2000/11/12");
		assert bestDate == "12/11/2000"
	}
	
	void testCorrectDate4() {
		String bestDate = bestBefore.getBestBeforeDate("1/30/0");
		assert bestDate == "30/1/2000"
	}
	
	
	void testWrongDate() {
		try {
			bestBefore.getBestBeforeDate("04/07");
			fail()
		} catch(Exception e) {
			assert e in BestBeforeException
		}
	}
	
	void testWrongDate2() {
		try {
			bestBefore.getBestBeforeDate("13, 13, 2999");
			fail()
		} catch(Exception e) {
			assert e in BestBeforeException
		}
	}
	
}
