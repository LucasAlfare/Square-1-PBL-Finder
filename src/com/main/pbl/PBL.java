package com.main.pbl;

/**
 * EESSA CLASSE REPRESENTA 1 PBL.
 */
public class PBL {

    private String name;
    private PLL topPLL, bottomPLL;

    public PBL(String name, PLL topPLL, PLL bottomPLL) {
        this.name = name;
        this.topPLL = topPLL;
        this.bottomPLL = bottomPLL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PLL getTopPLL() {
        return topPLL;
    }

    public void setTopPLL(PLL topPLL) {
        this.topPLL = topPLL;
    }

    public PLL getBottomPLL() {
        return bottomPLL;
    }

    public void setBottomPLL(PLL bottomPLL) {
        this.bottomPLL = bottomPLL;
    }

    @Override
    public String toString() {
        return this.name + "[" + this.topPLL.getName() + "/" + this.bottomPLL.getName() + "]";
    }
}
