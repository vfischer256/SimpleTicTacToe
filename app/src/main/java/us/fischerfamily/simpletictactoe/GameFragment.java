package us.fischerfamily.simpletictactoe;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.HashSet;
import java.util.Set;

public class GameFragment extends Fragment {
    // Data structures go here...

    static private int mTileIds[] = {R.id.tile1, R.id.tile2, R.id.tile3,
            R.id.tile4, R.id.tile5, R.id.tile6, R.id.tile7, R.id.tile8,
            R.id.tile9,};

    private Tile mEntireBoard = new Tile(this);
    private Tile tiles[] = new Tile[9];
    private Tile.Owner mPlayer = Tile.Owner.X;
    private Set<Tile> mAvailable = new HashSet<Tile>();
    private int mLast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
        //   initGame();
    }

    private void clearAvailable() {
        mAvailable.clear();
    }

    private void addAvailable(Tile tile) {
        mAvailable.add(tile);
    }

    public boolean isAvailable(Tile tile) {
        return mAvailable.contains(tile);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =
                inflater.inflate(R.layout.board, container, false);
        //    initViews(rootView);
        // updateAllTiles();
        return rootView;
    }

    private void initViews(View rootView) {
        mEntireBoard.setView(rootView);
        for (int tile = 0; tile < 9; tile++) {
            View outer = rootView.findViewById(mTileIds[tile]);
            ImageButton inner = (ImageButton) outer.findViewById
                    (mTileIds[tile]);


          /*  final int ftile = tile;
            final int fSmall = small;
            final Tile smallTile = mSmallTiles[tile][small];
            smallTile.setView(inner);
            inner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAvailable(smallTile)) {
                        makeMove(ftile, fSmall);
                        switchTurns();
                    }
                }
            });*/
        }
    }


    private void switchTurns() {
        mPlayer = mPlayer == Tile.Owner.X ? Tile.Owner.O : Tile
                .Owner.X;
    }

    private void makeMove(int large, int small) {
       /*
        mLast = large;
        mLastSmall = small;
        Tile smallTile = mSmallTiles[large][small];
        Tile largeTile = tiles[large];
        smallTile.setOwner(mPlayer);
        setAvailableFromLastMove(small);
        Tile.Owner oldWinner = largeTile.getOwner();
        Tile.Owner winner = largeTile.findWinner();
        if (winner != oldWinner) {
            largeTile.setOwner(winner);
        }
        winner = mEntireBoard.findWinner();
        mEntireBoard.setOwner(winner);
        updateAllTiles();
        if (winner != Tile.Owner.NEITHER) {
            ((GameActivity) getActivity()).reportWinner(winner);
        }
        */
    }

    public void restartGame() {
        initGame();
        initViews(getView());
        updateAllTiles();
    }

    public void initGame() {
        /*
        Log.d("UT3", "init game");
        mEntireBoard = new Tile(this);
        // Create all the tiles
        for (int large = 0; large < 9; large++) {
            tiles[large] = new Tile(this);
            for (int small = 0; small < 9; small++) {
                mSmallTiles[large][small] = new Tile(this);
            }
            tiles[large].setSubTiles(mSmallTiles[large]);
        }
        mEntireBoard.setSubTiles(tiles);

        // If the player moves first, set which spots are available
        mLastSmall = -1;
        mLast = -1;
        setAvailableFromLastMove(mLastSmall);
        */
    }

    private void setAvailableFromLastMove(int small) {
        /*
        clearAvailable();
        // Make all the tiles at the destination available
        if (small != -1) {
            for (int dest = 0; dest < 9; dest++) {
                Tile tile = mSmallTiles[small][dest];
                if (tile.getOwner() == Tile.Owner.NEITHER)
                    addAvailable(tile);
            }
        }
        // If there were none available, make all squares available
        if (mAvailable.isEmpty()) {
            setAllAvailable();
        }
        */
    }

    private void setAllAvailable() {
        /*
        for (int large = 0; large < 9; large++) {
            for (int small = 0; small < 9; small++) {
                Tile tile = mSmallTiles[large][small];
                if (tile.getOwner() == Tile.Owner.NEITHER)
                    addAvailable(tile);
            }
        }
        */
    }

    private void updateAllTiles() {
        /*
        mEntireBoard.updateDrawableState();
        for (int large = 0; large < 9; large++) {
            tiles[large].updateDrawableState();
            for (int small = 0; small < 9; small++) {
                mSmallTiles[large][small].updateDrawableState();
            }
        }
        */
    }

    /**
     * Create a string containing the state of the game.
     */


    public String getState() {

        StringBuilder builder = new StringBuilder();
        builder.append(mLast);
        builder.append(',');
        /*
        builder.append(mLastSmall);
        builder.append(',');
        for (int large = 0; large < 9; large++) {
            for (int small = 0; small < 9; small++) {
                builder.append(mSmallTiles[large][small].getOwner().name());
                builder.append(',');
            }
        }
        */

        return builder.toString();

    }


    /**
     * Restore the state of the game from the given string.
     */

    public void putState(String gameData) {
        String[] fields = gameData.split(",");
        int index = 0;
        mLast = Integer.parseInt(fields[index++]);
        /*
        mLastSmall = Integer.parseInt(fields[index++]);
        for (int large = 0; large < 9; large++) {
            for (int small = 0; small < 9; small++) {
                Tile.Owner owner = Tile.Owner.valueOf(fields[index++]);
                mSmallTiles[large][small].setOwner(owner);
            }
        }
        setAvailableFromLastMove(mLastSmall);
        updateAllTiles();
        */
    }

}

