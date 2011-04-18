package net.badgerhunt.antroid;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReplayDataRaw {
    public String challenge;
    public String date;
    public String[] players;
    public String[] playerstatus;
    public Integer[] submitids;
    public Integer[] user_ids;
    public String user_url;
    public String game_id;
    public String game_url;
    public String replayformat;
    public String replaydata;

    private DateFormat dateParser = new SimpleDateFormat("MM-dd-yyyy");

    public ReplayData bake() throws ParseException {
        ReplayData data = new ReplayData();
        data.challenge = challenge;
        data.date = dateParser.parse(date);
        for (int i = 0; i < players.length; i++) {
            data.players.add(new Player(players[i], playerstatus[i], submitids[i], user_ids[i]));
        }
        data.userURL = user_url;
        data.gameId = game_id;
        data.gameURL = game_url;
        data.replayFormat = replayformat;
        data.replayData = replaydata; // todo - parsed
        return data;
    }
}
