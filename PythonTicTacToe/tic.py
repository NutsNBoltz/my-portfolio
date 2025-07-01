import tkinter as tk
from tkinter import messagebox

# ---------- Global Game Variables ----------
current_player = "X"
board = []
buttons = []
board_size = 3  # Default
win_length = 3  # Always 3 in a row to win

# ---------- Start Menu ----------
def show_menu():
    menu = tk.Tk()
    menu.title("Choose Board Size")

    tk.Label(menu, text="Select Game Mode", font=("Arial", 16)).pack(pady=10)

    tk.Button(menu, text="3 x 3", font=("Arial", 14), width=10, command=lambda: start_game(menu, 3)).pack(pady=5)
    tk.Button(menu, text="4 x 4", font=("Arial", 14), width=10, command=lambda: start_game(menu, 4)).pack(pady=5)

    menu.mainloop()

# ---------- Game Logic ----------
def start_game(menu_window, size):
    global board_size, board, root
    board_size = size
    board = [""] * (board_size * board_size)
    menu_window.destroy()

    root = tk.Tk()
    root.title(f"{board_size}x{board_size} Tic Tac Toe")
    build_board()
    root.mainloop()

def build_board():
    global buttons
    buttons = []
    for i in range(board_size * board_size):
        b = tk.Button(root, text="", font=('Arial', 24), width=4, height=2,
                      command=lambda i=i: on_click(i))
        b.grid(row=i // board_size, column=i % board_size)
        buttons.append(b)

def check_winner():
    combos = []

    # Horizontal
    for row in range(board_size):
        for col in range(board_size - win_length + 1):
            start = row * board_size + col
            combos.append(tuple(start + i for i in range(win_length)))

    # Vertical
    for col in range(board_size):
        for row in range(board_size - win_length + 1):
            start = row * board_size + col
            combos.append(tuple(start + i * board_size for i in range(win_length)))

    # Diagonal ↘
    for row in range(board_size - win_length + 1):
        for col in range(board_size - win_length + 1):
            start = row * board_size + col
            combos.append(tuple(start + i * (board_size + 1) for i in range(win_length)))

    # Diagonal ↙
    for row in range(board_size - win_length + 1):
        for col in range(win_length - 1, board_size):
            start = row * board_size + col
            combos.append(tuple(start + i * (board_size - 1) for i in range(win_length)))

    for indices in combos:
        if all(board[i] == board[indices[0]] and board[i] != "" for i in indices):
            return board[indices[0]]

    if "" not in board:
        return "Draw"
    return None

def on_click(i):
    global current_player
    if board[i] == "":
        board[i] = current_player
        buttons[i].config(text=current_player, state="disabled")
        winner = check_winner()
        if winner:
            if winner == "Draw":
                messagebox.showinfo("Game Over", "It's a draw!")
            else:
                messagebox.showinfo("Game Over", f"Player {winner} wins!")
            root.destroy()
            show_menu()
        else:
            current_player = "O" if current_player == "X" else "X"

# ---------- Start the game ----------
show_menu()
