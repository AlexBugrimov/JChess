package dev.bug.chess.pieces;

import com.google.common.collect.ImmutableList;
import dev.bug.chess.Alliance;
import dev.bug.chess.board.Board;
import dev.bug.chess.board.BoardUtils;
import dev.bug.chess.board.Move;
import dev.bug.chess.board.Tile;

import java.util.ArrayList;
import java.util.List;

import static dev.bug.chess.board.BoardUtils.*;

/**
 * Рыцарь (конь)
 */
public class Knight extends Piece {

    private static final int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Knight(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATES) {
            final int candidateDestinationCoordinate = this.piecePosition + currentCandidateOffset;
            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isSecondColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isSeventhColumnExclusion(this.piecePosition, currentCandidateOffset)
                        || isEighthColumnExclusion(this.piecePosition, currentCandidateOffset)
                ) {
                    continue;
                }
                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                if (!candidateDestinationTile.isTileOccupied()) {
                    legalMoves.add(new Move());
                } else {
                    final Piece pieceDestination = candidateDestinationTile.getPiece();
                    final Alliance pieceAlliance = pieceDestination.getPieceAlliance();
                    if (this.pieceAlliance != pieceAlliance) {
                        legalMoves.add(new Move());
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return FIRST_COLUMN[currentPosition] && ((candidateOffset == -17)
                || (candidateOffset == -10)
                || (candidateOffset == 6)
                || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return SECOND_COLUMN[currentPosition] && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return SEVENTH_COLUMN[currentPosition] && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return EIGHTH_COLUMN[currentPosition] && ((candidateOffset == -15) || (candidateOffset == -6)
                || (candidateOffset == 10) || (candidateOffset == 17));
    }
}
