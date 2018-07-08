package UtilsPackage;


import java.io.*;
import java.util.*;


/**
 * Created by USER on 6/9/2017.
 */
public class WinnerTableLoader {
    private ArrayList<UserDetails> _winners;
    public WinnerTableLoader(){
        _winners=new ArrayList<>();
    }
    public void loader(String winnersFileName) throws IOException{
        _winners.clear();
        File f=new File(winnersFileName);
        f.createNewFile();
        BufferedReader br = new BufferedReader(new FileReader(winnersFileName));
        String line;
        String points;
        String time;
        String hp;
        String name;
        String levels;
        UserDetails UD;
        while ((line = br.readLine()) != null) {
            if(line.trim().isEmpty())
                continue;
            StringTokenizer st = new StringTokenizer(line, " ");
            levels = st.nextToken();
            name = st.nextToken();
            time = st.nextToken();
            hp = st.nextToken();
            points = st.nextToken();
            _winners.add(new UserDetails(levels, name, hp, time, points));
        }
       // System.out.println("ss");
        _winners.sort(new ComparatorWiners());
    }
    public Vector<String> getBestFive()
    {
        Vector<String> toReturn= new Vector<>();
        int end=5;
        if(_winners.size()<5)
            end=_winners.size();
        for (UserDetails ud: _winners.subList(0,end))
                toReturn.add(toReturn.size(),ud.toString());
        return toReturn;
    }

    public Vector<String> getWinnersByLevel(int level){
        Vector<String> toReturn= new Vector<>();
        for (UserDetails ud: _winners)
            if(ud.getLevelS().equals(""+level))
                toReturn.add(toReturn.size(),ud.toString());
        return toReturn;
    }


    public void writeWinner(UserDetails ud ,String fileName ) throws  IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName,true)) ;
        bw.newLine();
        bw.write((ud.getLevel()+1)+" "+ud.getFullNameString()+" "+ud.getTimeString()+" "+ud.getHpLeft()+" "+ud.getPointsString());
        bw.close();
        _winners.add(ud);
        _winners.sort(new ComparatorWiners());
    }

    public Vector<String> get_winners() {
        Vector<String> toReturn= new Vector<>();
        for (UserDetails ud: _winners)
            toReturn.add(toReturn.size(),ud.toString());
        return toReturn;
    }
}
