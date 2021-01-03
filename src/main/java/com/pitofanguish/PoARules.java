package com.pitofanguish;

public class PoARules {
    private static final int[][] RULES = new int[][]{
            //Row => character
            // Columns => type of item (Monster, Weapon, Potion, Gold)
            {-1, 0, 1, 2}
    };

    public int getResult(int effect) { return RULES[0][effect]; }
}
