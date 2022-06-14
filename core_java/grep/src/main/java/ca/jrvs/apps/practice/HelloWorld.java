package grep.src.main.java.ca.jrvs.apps.practice;

 class HelloWorld {
    public static void main(String args[]) {
        System.out.println("Hello, World");

        RegexExcImp.matchJpeg("Revan.jpeg, Michu.png, strider.JPG, aquila.Bmp, ThoR.jPEg, Nakita.RAW, pippIN.jpg");
        RegexExcImp.matchJpeg("michu.png");
        RegexExcImp.matchJpeg("aquila.JPeg");

        RegexExcImp.matchIp("000.111.222.444");
        RegexExcImp.matchIp("012.345.678.901");
        RegexExcImp.matchIp("00.111.222.444");

        RegexExcImp.isEmptyLine(" ");
        RegexExcImp.isEmptyLine("a");
        RegexExcImp.isEmptyLine("  this  has    weird     spacing...");
        RegexExcImp.isEmptyLine("     ");
        RegexExcImp.isEmptyLine("");

    }
}

