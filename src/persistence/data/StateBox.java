package persistence.data;

/**
 * The enum State box.
 */
public enum StateBox {
    /**
     * State intact : nothing happened, initial state.
     */
    intact,
    /**
     * State Burning : it is burning.
     */
    burning,
    /**
     * State Scorched : it burned, but can burn again.
     */
    scorched,
    /**
     * State Dust : it burned, and it can't burn again.
     */
    dust;
}
