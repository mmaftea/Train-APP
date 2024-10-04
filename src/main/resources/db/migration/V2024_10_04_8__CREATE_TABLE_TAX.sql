CREATE TABLE Expense(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        category VARCHAR(20) ,
                        expense_price FLOAT
);
CREATE TABLE TAX(
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    tax_base_ticket_id INT,
                    tax_expense_id INT,

                    FOREIGN KEY (tax_base_ticket_id) REFERENCES BaseTicket(id),
                    FOREIGN KEY (tax_expense_id) REFERENCES Expense(id)
);