package dev.bug.chess.board;

import com.google.common.collect.ImmutableMap;
import dev.bug.chess.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

/**
 * Плитка
 */
public abstract class Tile {

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTiles = new HashMap<>();
        for (int i = 0; i < 64; i++) {
            emptyTiles.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTiles);
    }

    protected final int tileCoordinate;

    private Tile(int tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return nonNull(piece) ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {

        EmptyTile(final int tileCoordinate) {
            super(tileCoordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;

        private OccupiedTile(int tileCoordinate, Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }
}
