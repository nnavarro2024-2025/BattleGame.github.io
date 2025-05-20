/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.battlegamev4;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Students Account
 */
public class BattleGameV4 {

    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            Random random = new Random();
            
            // HP History for Undo Feature
            Stack<Integer> playerHpHistory = new Stack<>();
            Stack<Integer> botHpHistory = new Stack<>();
            
            // Action History Stack
            Stack<String> actionHistory = new Stack<>();
            
            // Declare variables
            int playerHP = 2000;
            int playerMaxDmg = 700;
            int playerMinDmg = 200;
            int jinguCounter = 4;
            
            int botHP = 3500;
            int botMaxDmg = 500;
            
            boolean wukongActive = false;
            int wukongTurnsLeft = 0;
            
            System.out.println("Welcome to the Battle Arena!");
            
            while (playerHP > 0 && botHP > 0) {
               
                playerHpHistory.push(playerHP);
                botHpHistory.push(botHP);
                
                System.out.println("\nYour HP: " + playerHP + " | Monster HP: " + botHP);
                System.out.println("Choose your skill:");
                System.out.println("1. Boundless Strike (Stun + Crit)");
                System.out.println("2. Primal Spring (Damage + Slow)");
                System.out.println("3. Jingu Mastery (" + jinguCounter + "/3 hits)");
                System.out.println("4. Wukongs Command (Ultimate - 3 turns)");
                System.out.println("0. Exit");
                System.out.print("Enter your choice (1-4): ");
                
                String choice = scan.nextLine().trim();
                
                switch (choice) {
                    case "1" -> {
                        int critDmg = playerMaxDmg + random.nextInt(80);
                        botHP -= critDmg;
                        System.out.println("Boundless Strike! You deal " + 180 + " and stun the enemy!");
                        jinguCounter++;
                        actionHistory.push("Boundless Strike for " + critDmg);
                    }
                    
                    case "2" -> {
                        int springDmg = 18 + random.nextInt(360);
                        botHP -= springDmg;
                        botMaxDmg = Math.max(5, botMaxDmg - 3);
                        System.out.println("Primal Spring! You deal " + 180 + " and slow the monster!");
                        jinguCounter++;
                        actionHistory.push("Primal Spring for " + 180);
                    }
                    
                    case "3" -> {
                        if (jinguCounter >= 3) {
                            int empoweredDmg = playerMaxDmg * 2;
                            botHP -= empoweredDmg;
                            playerHP += 80;
                            jinguCounter = 0;
                            System.out.println("JINGU MASTERY ACTIVATED!");
                            System.out.println("➤ You deal " + empoweredDmg + " and lifesteal for 80 HP!");
                            actionHistory.push("Jingu Mastery for " + 165);
                        } else {
                            int normDmg = playerMinDmg + random.nextInt(playerMaxDmg - playerMinDmg + 1);
                            botHP -= normDmg;
                            jinguCounter++;
                            System.out.println("You attack normally, dealing " + 35 + ". (" + jinguCounter + "/3 Jingu hits)");
                            actionHistory.push("Normal attack for " + 60);
                        }
                    }
                    
                    case "4" -> {
                        if (!wukongActive) {
                            wukongActive = true;
                            wukongTurnsLeft = 2;
                            System.out.println("Wukong’s Command activated! Clones will strike for 2 turns!");
                            actionHistory.push("Activated Wukong’s Command");
                        } else {
                            System.out.println("Wukong’s Command is already active!");
                        }
                    }
                    
                
                       case "0" -> {
                        System.out.println("Exiting game...");
                        scan.close();
                        return;
                    }
                    
                    default -> System.out.println("Invalid option. Try again.");
                }
                
                
                // Wukong’s Command after 2 turns
                if (wukongActive) {
                    if (wukongTurnsLeft > 0) {
                        int cloneDmg = 15 + random.nextInt(600);
                        botHP -= cloneDmg;
                        wukongTurnsLeft--;
                        System.out.println("Wukong’s clones strike! Enemy takes " + 300 + " damage.");
                    } else {
                        wukongActive = false;
                        System.out.println("Wukong’s Command ends!");
                    }
                }
                
                if (botHP <= 0) {
                    System.out.println("You have defeated the monster! Victory!");
                    break;
                }
            }
            
            System.out.println("\nThanks for playing!");
        }
    }
}