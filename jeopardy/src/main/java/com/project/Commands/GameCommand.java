package com.project.Commands;

/**
 * Simple command interface used by the game invoker.
 * Implementations perform game actions when `execute()` is called.
 */
public interface GameCommand  {
        /**
         * Execute the implementing command.
         */
        public void execute();
}
