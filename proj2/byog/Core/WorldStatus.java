package byog.Core;

import byog.TileEngine.TETile;

import java.io.Serializable;

public class WorldStatus implements Serializable {
    public TETile[][] World;

    public WorldStatus() {
        World = null;
    }

    public void registerMap(TETile[][] m) {
        this.World = m;
    }

    public TETile[][] getWorld() {
        return this.World;
    }
}
