package dataProviders;

import org.testng.annotations.DataProvider;

public class UsersInfoDataProvider {
	
	@DataProvider(name="UsersInfoDataProvider")
	public static Object[][] Usersdata(){
		
		return new Object[][] {
			{"Layth Zaid", "Laythzz@gmail.com", "Yogyakarta, Indonesia", "Indonesia", true},
			{"Sloppery Slope", "Slopzzbobs@hubs.oke", "Bethlehm, Palestine", "Palestine", true},
			{"", "dasd@hotmail.com", "", "", true},
			{"Glumpy Glimpse", "garbageEmail", "Glumps", "Glumps forever", false},
			{"Jermy Sueko", null, "Jogja, Indonesia", "Phillipnes", false},
			{"Ryry Hang", "ThearyHan0@here.com", "Bali, Indonesia", "Cambodia", true}

		};
				
	}

}
