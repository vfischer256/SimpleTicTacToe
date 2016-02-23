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
    private Tile mTiles[] = new Tile[9];
    private Tile.Owner mPlayer = Tile.Owner.X;
    private Set<Tile> mAvailable = new HashSet<Tile>();
    private int mLast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
        initGame();
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
        initViews(rootView);
        updateAllTiles();
        return rootView;
    }

    private void initViews(View rootView) {
        mEntireBoard.setView(rootView);

        for (int i = 0; i < 9; i++) {
            ImageButton tileButton = (ImageButton) rootView.findViewById
                    (mTileIds[i]);

            final int fTile = i;
            final Tile tile = mTiles[i];
            tile.setView(tileButton);
            tileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAvailable(tile)) {
                        makeMove(fTile);
                        switchTurns();
                    }
                }
            });
        }

    }

    private void switchTurns() {
        mPlayer = mPlayer == Tile.Owner.X ? Tile.Owner.O : Tile.Owner.X;
    }

    private void makeMove(int pos) {

        mLast = pos;
        Tile tile = mTiles[pos];

        tile.setOwner(mPlayer);
        setAvailableFromLastMove(pos);

        Tile.Owner winner = mEntireBoard.findWinner();
        mEntireBoard.setOwner(winner);
        updateAllTiles();
        if (winner != Tile.Owner.NEITHER) {
            ((GameActivity) getActivity()).reportWinner(winner);
        }
    }

    public void initGame() {
        Log.d("UT3", "init game");
        mEntireBoard = new Tile(this);
        // Create all the tiles

        for (int i = 0; i < 9; i++) {
            mTiles[i] = new Tile(this);
        }

        mEntireBoard.setSubTiles(mTiles);

        // If the player moves first, set which spots are available
        mLast = -1;
        setAvailableFromLastMove(mLast);
    }

    private void setAvailableFromLastMove(int pos) {
        clearAvailable();
        // Make all the tiles at the destination available
        if (pos != -1) {
            Tile tile = mTiles[pos];
            if (tile.getOwner() == Tile.Owner.NEITHER)
                addAvailable(tile);

        }
        // If there were none available, make all squares available
        if (mAvailable.isEmpty()) {
            setAllAvailable();
        }
    }

    private void setAllAvailable() {

        for (int i = 0; i < 9; i++) {
            Tile tile = mTiles[i];
            if (tile.getOwner() == Tile.Owner.NEITHER)
                addAvailable(tile);
        }

    }

    private void updateAllTiles() {
        mEntireBoard.updateDrawableState();

        for (int i = 0; i < 9; i++) {
            mTiles[i].updateDrawableState();
        }

    }

    /**
     * Create a string containing the state of the game.
     */
    public String getState() {
        StringBuilder builder = new StringBuilder();
        builder.append(mLast);
        builder.append(',');

        for (int i = 0; i < 9; i++) {
            builder.append(mTiles[i].getOwner().name());
            builder.append(',');
        }

        return builder.toString();
    }

    /**
     * Restore the state of the game from the given string.
     */
    public void putState(String gameData) {
        String[] fields = gameData.split(",");
        int index = 0;
        mLast = Integer.parseInt(fields[index++]);

        for (int i = 0; i < 9; i++) {
            Tile.Owner owner = Tile.Owner.valueOf(fields[index++]);
            mTiles[i].setOwner(owner);
        }

        setAvailableFromLastMove(mLast);
        updateAllTiles();
    }
}

