package menus;

import java.util.ArrayList;

import main.Main;

public class NameGenerator {
	
	private static String[] consonants = {
						   "b", "bl", "br",
						   "c", "ch", "cl", "cr",
						   "d", "dr",
						   "f", "fl", "fr",
						   "g", "gl", "gr",
						   "h",
						   "j",
						   "k",
						   "l",
						   "m",
						   "n",
						   "p", "ph", "pl", "pr",
						   "qu",
						   "r",
						   "s", "sh", "st", "sm", "sn", "sl", "sp", "sk", "sc", "squ", "str", "spr", "scr", "shr",
						   "t", "tr", "th", "thr",
						   "v",
						   "w",
						   "y",
						   "z"
	};
	private static String[] endConsonants = {
			"b",
			"ck",
			"d", "dge",
			"f",
			"g", "gue",
			"l", "ld", "lt", "lb", "lp", "lge", "lk",
			"m", "mb", "mp",
			"n", "ng", "nk", "nd", "nt",
			"p",
			"que",
			"r", "rm", "rn", "rt", "rd", "rk", "rg", "rb", "rp",
			"s", "sh", "st", "sp", "sk",
			"t", "th", "tch",
			"x",
			"y",
			"w",
			"z"
	};
	private static String[] vowels = {
		"a", "a", "a", "e", "e", "e", "e", "i", "i", "o", "o", "o", "u", "u", "ee", "oo"
	};
	
	//all the words that are too offensive for this game or are too hard to pronounce
	private static String[] filter = {
		"stupid", "idiot", "loser", "dumb", "sex", "rape", "rapist", "perv", "shit", "fuck", "fuk", "ass", "whore", "bitch", "vagina", "boob", "penis", "dick", "cock", "damn", "crap", "gay", "lesbian", "milf", "porn", "naked", "slut", "prostitute", "butt", "testicle", "teste", "balls", "nipple", "bra", "hell", "sperm", "masturbate", "poo", "fag", "skank",
		"yy", "ww", "eemp", "eemb", "sque", "squi", "quie", "quu", "yi", "sce", "sci", "iy", "iw", "uy", "uw", "eey", "eew", "oow", "ooy", "oor", "rar", "rer", "rir", "ror", "rur", "reer", "quee", "quoo", "yee", "quou", "oong", "eeng", "lal", "lel", "lil", "lol", "lul", "leel", "lool"
	};
	private static ArrayList<String> lastNames = new ArrayList<String>();
	
	public static String newName() {
		String result;
		do {
			result = "";
			boolean vowel = randInt(3) == 0;
			int num = randInt(3)+2;
			for(int i = 0; i < num; i++) {
				if(vowel) {
					if(i == num-1 && num == 2) {
						String letter = "";
						letter = vowels[randInt(vowels.length)];
						if(letter.equals("u") && result.length() == 1) letter = "ue";
						else if(letter.equals("u")) letter = "ew";
						else if(letter.equals("o") && result.length() > 1) letter = "ow";
						else if(letter.equals("a") && result.length() > 1) letter = "aw";
						else if(letter.equals("e") && result.length() > 1) letter = "ee";
						result = result + letter;
					}else if(i == 0) {
						String letter = "";
						do{
							letter = vowels[randInt(vowels.length)];
						}while(letter.equals("oo") || letter.equals("ee"));
						result = result + letter;
					}else {
						result = result + vowels[randInt(vowels.length)];
					}
				}else {
					if(i == num-1) {
						result = result + endConsonants[randInt(endConsonants.length)];
					}else {
						result = result + consonants[randInt(consonants.length)];
					}
				}
				vowel = !vowel;
			}
			result = modifiedString(result);
		}while(needsFiltering(result));
		lastNames.add(result);
		result = result.substring(0, 1).toUpperCase() + result.substring(1);
		return result;
	}
	
	private static String modifiedString(String str) {
		str = str.replaceAll("eel", "il");
		str = str.replaceAll("ool", "ul");
		str = str.replaceAll("ree", "ri");
		str = str.replaceAll("roo", "ru");
		str = str.replaceAll("ul", "ol");
		str = str.replaceAll("oock", "ook");
		str = str.replaceAll("eeck", "eek");
		str = str.replaceAll("cee", "cie");
		str = str.replaceAll("phee", "fee");
		str = str.replaceAll("phoo", "foo");
		str = str.replaceAll("thoo", "thu");
		str = str.replaceAll("thee", "thi");
		str = str.replaceAll("oosk", "usk");
		str = str.replaceAll("oosp", "usp");
		str = str.replaceAll("oost", "ust");
		str = str.replaceAll("eesk", "isk");
		str = str.replaceAll("eesp", "isp");
		str = str.replaceAll("eest", "ist");
		str = str.replaceAll("je", "ge");
		str = str.replaceAll("ji", "gi");
		str = str.replaceAll("ka", "ca");
		str = str.replaceAll("ko", "co");
		str = str.replaceAll("ku", "cu");
		str = str.replaceAll("eex", "ix");
		str = str.replaceAll("eer", "ir");
		str = str.replaceAll("eel", "il");
		str = str.replaceAll("oor", "ur");
		str = str.replaceAll("ool", "ul");
		str = str.replaceAll("oodg", "udge");
		str = str.replaceAll("eedg", "idge");
		str = changeEnd(str, "shee", "shi");
		str = changeEnd(str, "shoo", "shu");
		str = changeEnd(str, "il", "ill");
		str = changeEnd(str, "ey", "ay");
		str = changeEnd(str, "oo", "ue");
		str = changeEnd(str, "je", "ge");
		str = changeEnd(str, "eege", "idge");
		str = changeEnd(str, "re", "er");
		str = changeEnd(str, "ru", "rew");
		str = changeEnd(str, "i", "y");
		str = changeEnd(str, "uy", "ui");
		return str;
	}
	private static String changeEnd(String str, String oldSuffix, String newSuffix) {
		if(str.endsWith(oldSuffix)) {
			str = str.substring(0, str.length() - oldSuffix.length());
			str = str + newSuffix;
		}
		return str;
	}
	private static boolean needsFiltering(String str) {
		for(int i = 0; i < filter.length; i++) {
			if(str.contains(filter[i])) {
				return true;
			}
		}
		for(int i = 0; i < lastNames.size(); i++) {
			if(str.equals(lastNames.get(i))) {
				return true;
			}
		}
		return str.length() > 10;
	}
	
	private static int randInt(int bound) {
		return (int) (Math.random()*bound);
	}
	
}
