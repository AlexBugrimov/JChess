package dev.bug.chess.pieces;

import dev.bug.chess.Alliance;
import dev.bug.chess.board.Board;
import dev.bug.chess.board.Move;

import java.util.Collection;
import java.util.List;

/**
 * Часть
 */
public abstract class Piece {

    protected final int piecePosition;
    protected final Alliance pieceAlliance;

    Piece(final int piecePosition, final Alliance pieceAlliance) {
        this.piecePosition = piecePosition;
        this.pieceAlliance = pieceAlliance;
    }

    public Alliance getPieceAlliance() {
        return pieceAlliance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
