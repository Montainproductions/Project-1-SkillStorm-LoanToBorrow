
const LOAN_URL = "http://localhost:8080/loans";
const BORROWER_URL = "http://localhost:8080/borrowers";

document.addEventListener("DOMContentLoaded", () => {
    let loanxhr = new XMLHttpRequest();

    loanxhr.onreadystatechange = () =>{
        if(loanxhr.readyState === 4){
            if (loanxhr.status >= 200 && loanxhr.status < 300) {
                const data = JSON.parse(loanxhr.responseText);
                
            } else {
                document.getElementById('statusMsg').textContent = 'Request failed: ' + loanxhr.status;
            }
        }
    }
    
    /* Loans */
    loanxhr.open("GET", LOAN_URL);

    loanxhr.send();

    /* Borrower
    let borrowerxhr = new XMLHttpRequest();

    borrowerxhr.onreadystatechange = () =>{
        if(borrowerxhr.readyState === 4){
            if (borrowerxhr.status >= 200 && borrowerxhr.status < 300) {
                const data = JSON.parse(borrowerxhr.responseText);
                
            } else {
                document.getElementById('statusMsg').textContent = 'Request failed: ' + borrowerxhr.status;
            }
        }
    }
    
    borrowerxhr.open("GET", BORROWER_URL);

    borrowerxhr.send();*/
})

const addLoanToList = (listOfLoans) =>{
    const container = document.getElementById('loanList');

    // Clear existing entries before re-rendering
    container.innerHTML = '';

    listOfLoans.forEach(function(loan) {
        const template = document.querySelector('.loan-template');
        const newVersionOfLoan = template.cloneNode(true);

        // Make it visible
        newVersionOfLoan.style.display = '';
        newVersionOfLoan.classList.remove('loan-template');

        // Fill in the values from the server response
        newVersionOfLoan.querySelector('.loanName').textContent      = loan.loanName;
        newVersionOfLoan.querySelector('.borrowername').textContent  = loan.borrowerName;
        newVersionOfLoan.querySelector('.loan-amount').textContent    = '$ ' + loan.loanAmount;
        newVersionOfLoan.querySelector('.payment-date').textContent   = loan.paymentDate;
        newVersionOfLoan.querySelector('.payment-amount').textContent = '$ ' + loan.paymentAmount;

        // Set the status oval color based on loan status
        setStatus(newVersionOfLoan.querySelector('.loanBorrower-status'), loan.riskValue);

        // Attach click to load this loan's detail on the right panel
        newVersionOfLoan.querySelector('button').addEventListener('click', function() {
            loadLoanDetail(loan.id);
        });

        container.appendChild(clone);
    });
}

const addBorrowerToList = (listOfBorrowers) =>{
    const container = document.getElementById('borrowerList');

    // Clear existing entries before re-rendering
    container.innerHTML = '';

    listOfBorrowers.forEach(function(borrower) {
        const template = document.querySelector('.loan-template');
        const newVersionOfLoan = template.cloneNode(true);

        // Make it visible
        newVersionOfLoan.style.display = '';
        newVersionOfLoan.classList.remove('borrowerTemplateButton');

        // Fill in the values from the server response
        newVersionOfLoan.querySelector('.borrowername').textContent = borrower.borrowerName;
        newVersionOfLoan.querySelector('.borrowerCreditScore').textContent = borrower.borrowerName;
        newVersionOfLoan.querySelector('.loan-amount').textContent = '$ ' + borrower.loanAmount;

        // Set the status oval color based on loan status
        setStatus(newVersionOfLoan.querySelector('.borrower-status'), borrower.riskValue);

        // Attach click to load this loan's detail on the right panel
        newVersionOfLoan.querySelector('button').addEventListener('click', function() {
            loadLoanDetail(loan.id);
        });

        container.appendChild(clone);
    });
}

function setStatus(state) {
    const colors = {
        active:   '#1D9E75',
        late:     '#E24B4A',
        pending:  '#EF9F27',
        default:  '#6900e1'
    };
    oval.style.background = colors[state] || colors.default;
}

const newLoanForm = document.getElementById("newLoanForm");

if(newLoanForm){
    newLoanForm.addEventListener("submit", (event) => {
        event.preventDefault();
        
        const inputData = new FormData(document.getElementById("newLoanForm"));

        const fullLoanInfo = {
            movie: {
                name : inputData.get("loanName"),         
                loanType : inputData.get("loanType"),
                loanAmount : inputData.get("loanAmount"),
                lengthOfLoan : inputData.get("lengthOfLoan"),
                isAdjustableRate : inputData.get("isAdjustableRate"),
                initialInterestRate : inputData.get("initialInterestRate"),
                adjustedInterestRate : inputData.get("adjustedInterestRate"),
                monthInterestRateChanges : inputData.get("monthInterestRateChanges"),
                paymentDate : inputData.get("paymentDate")
            },
            borrower: {
                firstName : inputData.get("borrowerFirstName"),
                lastName : inputData.get("borrowerLastName"),
                borrowerEmail : inputData.get("borrowerEmail"),
                borrowerPhoneNumber : inputData.get("borrowerPhoneNumber"),
                borrowerEmploymentType : inputData.get("borrowerEmploymentType"),
                borrowerCreditScore : inputData.get("borrowerCreditScore"),
                borrowerAddress : inputData.get("borrowerAddress"),
                borrowerZipCode : inputData.get("borrowerZipCode"),
                borrowerUSState : inputData.get("borrowerUSState"),
                borrowerMonthlyIncome : inputData.get("borrowerMonthlyIncome"),
            }
        }

        PostNewLoan(fullLoanInfo);
    });
}

const PostNewLoan = async (newLoan) =>{
    const httpResponse = await fetch(LOAN_URL, {
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body : JSON.stringify(newLoan)
    })
    .then((httpResponse) => {
        return httpResponse.json();
    })
    .then((loanJson) => {
        window.location.href = 'LoanDisplay.html';
    })
    .catch((error) => {
        //Handles 400 and 500 status code

        document.getElementById('statusMsg').textContent = 'Failed to save loan.';
    })
}

//Update Loan from form
const updateLoanElement = document.getElementById("updateLoanForm")

if(updateLoanElement){
    updateLoanElement.addEventListener("submit", (event) => {
        event.preventDefault();

        const inputData = new FormData(document.getElementById("updateLoanForm"));

        const fullLoanInfo = {
            movie: {
                id : loanToUpdate.id,
                name : inputData.get("loanName").trim(),         
                loanType : inputData.get("loanType").trim(),
                loanAmount : inputData.get("loanAmount").trim(),
                lengthOfLoan : inputData.get("lengthOfLoan").trim(),
                isAdjustableRate : inputData.get("isAdjustableRate").trim(),
                initialInterestRate : inputData.get("initialInterestRate").trim(),
                adjustedInterestRate : inputData.get("adjustedInterestRate").trim(),
                monthInterestRateChanges : inputData.get("monthInterestRateChanges").trim(),
                paymentDate : inputData.get("paymentDate").trim()
            },
            borrower: {
                id : loanToUpdate.borrower.id,
                firstName : inputData.get("borrowerFirstName"),
                lastName : inputData.get("borrowerLastName"),
                borrowerEmail : inputData.get("borrowerEmail"),
                borrowerPhoneNumber : inputData.get("borrowerPhoneNumber"),
                borrowerEmploymentType : inputData.get("borrowerEmploymentType"),
                borrowerCreditScore : inputData.get("borrowerCreditScore"),
                borrowerAddress : inputData.get("borrowerAddress"),
                borrowerZipCode : inputData.get("borrowerZipCode"),
                borrowerUSState : inputData.get("borrowerUSState"),
                borrowerMonthlyIncome : inputData.get("borrowerMonthlyIncome"),
                borrowerYearlyIncome : inputData.get("borrowerYearlyIncome")
            }
        }

        fetch(LOAN_URL + `/${loanToUpdate.id}`, {
            method : "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body : JSON.stringify(fullLoanInfo)
        })
        .then((httpResponse) => {
            return httpResponse.json();
        })
        .then((movieJson) => {
            window.location.href='LoanDisplay.html'
        })
        .catch((error) => {
            //Handles 400 and 500 status code

            document.getElementById('statusMsg').textContent = 'Failed to edit loan.';
        })
    });
}
//Delete Loan
const deleteLoanElement = document.getElementById("deleteLoan")

if(deleteLoanElement){
    deleteLoanElement.addEventListener("submit", (event) => {
        event.preventDefault();		// prevent default form actions from occuring

        // sending delete request
        fetch(LOAN_URL + `/${loanToDelete.id}`, {
            method : "DELETE",
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then((httpRespone) => {

            // Make sure that the code returned is the no data code 
            if(httpRespone.status === 204) {
                removeLoanFromTable(loanToDelete.id);
                window.location.href='LoanDisplay.html'
            }
        })
        .catch((error) => {
            document.getElementById('statusMsg').textContent = 'Failed to delete loan.';
        })
    });
}

/*
* Borrower info
*/
//Save/New Borrower
const newBorrowerElement = document.getElementById("newBorrowerForm")

if(newBorrowerElement){
    newBorrowerElement.addEventListener("submit", (event) => {
        event.preventDefault();

        const inputData = new FormData(document.getElementById("newBorrowerForm"));

        const fullBorrowerInfo = {
            borrower: {
                id : borrowerToUpdate.id,
                firstName : inputData.get("borrowerFirstName"),
                lastName : inputData.get("borrowerLastName"),
                borrowerEmail : inputData.get("borrowerEmail"),
                borrowerPhoneNumber : inputData.get("borrowerPhoneNumber"),
                borrowerEmploymentType : inputData.get("borrowerEmploymentType"),
                borrowerCreditScore : inputData.get("borrowerCreditScore"),
                borrowerAddress : inputData.get("borrowerAddress"),
                borrowerZipCode : inputData.get("borrowerZipCode"),
                borrowerUSState : inputData.get("borrowerUSState"),
                borrowerMonthlyIncome : inputData.get("borrowerMonthlyIncome"),
            }
        }

        PostNewBorrower(fullBorrowerInfo);
    });
}

const PostNewBorrower = async (newBorrower) =>{
    const httpResponse = await fetch(BORROWER_URL, {
        method : "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body : JSON.stringify(newBorrower)
    })
    .then((httpResponse) => {
        return httpResponse.json();
    })
    .then((borrowerJson) => {
        //updateBorrowerInTable(borrowerJson);

        window.location.href = 'BorrowerDisplay.html';
    })
    .catch((error) => {
        //Handles 400 and 500 status code

        document.getElementById('statusMsg').textContent = 'Failed to save loan.';
    })
}

//Update Loan from form
const updateBorrowerElement = document.getElementById("updateBorrowerForm")

if(updateBorrowerElement){
    updateBorrowerElement.addEventListener("submit", (event) => {
        event.preventDefault();

        const inputData = new FormData(document.getElementById("updateBorrowerForm"));

        const fullBorrowerInfo = {
            borrower: {
                id : borrowerToUpdate.id,
                firstName : inputData.get("borrowerFirstName"),
                lastName : inputData.get("borrowerLastName"),
                borrowerEmail : inputData.get("borrowerEmail"),
                borrowerPhoneNumber : inputData.get("borrowerPhoneNumber"),
                borrowerEmploymentType : inputData.get("borrowerEmploymentType"),
                borrowerCreditScore : inputData.get("borrowerCreditScore"),
                borrowerAddress : inputData.get("borrowerAddress"),
                borrowerZipCode : inputData.get("borrowerZipCode"),
                borrowerUSState : inputData.get("borrowerUSState"),
                borrowerMonthlyIncome : inputData.get("borrowerMonthlyIncome"),
                borrowerYearlyIncome : inputData.get("borrowerYearlyIncome")
            }
        }

        fetch(BORROWER_URL + `/${borrowerToUpdate.id}`, {
            method : "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body : JSON.stringify(fullBorrowerInfo)
        })
        .then((httpResponse) => {
            return httpResponse.json();
        })
        .then((movieJson) => {
            // adding the updated movie to our table
            //updateMovieInTable(movieJson);

            window.location.href='BorrowerDisplay.html'
        })
        .catch((error) => {
            //Handles 400 and 500 status code

            document.getElementById('statusMsg').textContent = 'Failed to add borrower.';
        })
    });
}

//Delete Borrower
const deleteBorrowerElement = document.getElementById("deleteBorrower")

if(deleteBorrowerElement){
    deleteBorrowerElement.addEventListener("submit", (event) => {
        event.preventDefault();

        // sending delete request
        fetch(BORROWER_URL + `/${loanToDelete.id}`, {
            method : "DELETE",
            headers: {
                "Content-Type": "application/json",
            }
        })
        .then((httpRespone) => {

            // Make sure that the code returned is the no data code 
            if(httpRespone.status === 204) {
                window.location.href='BorrowerDisplay.html'
                removeBorrowerFromTable(borrowerToDelete.id);
            }
        })
        .catch((error) => {
            document.getElementById('statusMsg').textContent = 'Failed to add borrower.';
        })
    });
}

function loadBorrower(id) {
  if (!borrowerId) {
    showMessage('No borrower ID found in the URL.', 'error');
    return;
  }

  fetch(`http://localhost:8080/api/borrowers/${borrowerId}`)
    .then(res => {
      if (!res.ok) throw new Error('Borrower not found');
      return res.json();
    })
    .then(borrower => prefillForm(borrower))
    .catch(() => showMessage('Failed to load borrower data.', 'error'));
}

const removeMovieFromTable = (movieId) => {
    const tr = document.getElementById("TR-" + movieId);
    tr.remove();
}