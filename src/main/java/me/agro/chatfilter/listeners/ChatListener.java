package me.agro.chatfilter.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener {



    private String censorChat(String originalMessage) {
        String[] censoredWords = new String[] { "fuck", "shit", "idiot" };


        Pattern whitespacePattern = Pattern.compile("\\s");
        Matcher whitespaceMatcher = whitespacePattern.matcher(originalMessage);

        Pattern SpecialPattern = Pattern.compile("\\W");
        Matcher specialMatcher = SpecialPattern.matcher(originalMessage);


        int count = 0;
        while (whitespaceMatcher.find()) count++;

        while (specialMatcher.find()) count++;

        //Grab every index of a bypass Character
        int[] bypassIndex = new int[count];
        int lastIndex = 0;

        for (int i = 0; i < count; i++) {
            bypassIndex[i] = originalMessage.indexOf(' ', lastIndex);
            bypassIndex[i] = originalMessage.indexOf('-', lastIndex);
            lastIndex = bypassIndex[i] + 1;
        }



        String spaceCensorMessage = originalMessage.replaceAll("\\s", ""); // Remove All White Space

        String specialCensorMessage = spaceCensorMessage.replaceAll("\\W", ""); //Remove All Dashes

        String FinalMessage = "";

        for (String censored : censoredWords) {
            if(specialCensorMessage.contains(censored)) {
                FinalMessage = "";
            } else {
                FinalMessage = originalMessage;
            }
        }
        return FinalMessage;
    }


    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();

        String censoredMessage = censorChat(message);
        if(!censoredMessage.equals("")) {
            e.setMessage(censoredMessage);
        } else{
            e.setCancelled(true);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlease do not swear!"));
        }
        }
}