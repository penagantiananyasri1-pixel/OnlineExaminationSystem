
let questions = [];

async function loadQuestions() {

    try {
        const response = await fetch("/api/questions");

        questions = await response.json();

        const container = document.getElementById("questions");

        container.innerHTML = "";

        questions.forEach((q, index) => {

            let html = `
                <div class="question">
                    <h3>${index + 1}. ${q.question}</h3>
            `;

            q.options.forEach((option, i) => {

                html += `
                    <label>
                        <input type="radio"
                               name="q${index}"
                               value="${i}">
                        ${option}
                    </label>
                `;

            });

            html += "</div>";

            container.innerHTML += html;

        });

    } catch (error) {
        document.getElementById("questions").innerText =
            "Unable to load questions. Please refresh the page.";
    }
}

async function submitExam() {

    let answers = [];

    for (let i = 0; i < questions.length; i++) {

        const selected = document.querySelector(
            `input[name="q${i}"]:checked`
        );

        if (!selected) {
            alert("Please answer all questions!");
            return;
        }

        answers.push(Number(selected.value));
    }

    try {

        const response = await fetch("/api/submit", {

            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify({
                answers: answers
            })

        });

        const result = await response.json();

        if (!response.ok) {
            throw new Error(result.message || "Submission failed");
        }

        document.getElementById("result").innerText =
            "Your Score: " + result.score +
            "/" + result.totalQuestions;

    } catch (error) {

        alert(error.message);

    }
}

loadQuestions();
