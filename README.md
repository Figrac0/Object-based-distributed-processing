# 🎯 Guess the Number Game (JSP + Servlet)

**A simple web-based number guessing game built using Java Servlets and JSP.**  
The computer tries to guess the number you're thinking of using binary search logic.

---

## 🚀 Features

- *Start by selecting a range (min and max values)*
- *Let the computer guess your number*
- *Guide it by clicking: Greater / Less / Equal*
- *Detects cheating if the input is inconsistent*
- *Error page handling via exception catching and redirecting*
- *Basic styling with inline CSS for a better look*


---

## 🛠️ Technologies Used

- **Java 17**
- **JSP (Java Server Pages)**
- **Servlets (Java EE)**
- **Apache Tomcat 9+**
- **Maven** for project building and dependency management

---

## 🔧 How It Works

1. You choose a number range (e.g. *1 to 100*)
2. The computer picks the middle value
3. You tell it if the number is *greater*, *less*, or *equal*
4. The app narrows the range until it guesses your number
5. *Cheating* is detected if the logic becomes impossible
6. All logic is handled in the `GameServlet.java`

---

## ⚙️ Run Instructions

1. **Clone** the repository:
   ```bash
   git clone https://github.com/your-username/guess-number-game.git
