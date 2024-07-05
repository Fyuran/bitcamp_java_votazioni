var votes =
{
    'mauro': 5,
    'alessio': 4,
    'daniel': 4,
    'marta': 11,
    'mario': 11
};

function GetParticipantWithMoreVotes() {
    let maxVote = 0;
    let participants = [];


    for (let key in votes) {
        if (votes.hasOwnProperty(key)) {
            if (votes[key] > maxVote) {
                participants = [];
                let participant = key;
                maxVote = votes[key];
                participants.push({ [key]: votes[participant] });
            }

            else if (votes[key] === maxVote) {
                let participant = key;
                participants.push({ [key]: votes[participant] });
            }
        }
    }
    return participants;
}


function GetParticipantWithLessVotes(maxVote) {
    let minVote = maxVote;
    let participants = [];

    for (let key in votes) {
        if (votes.hasOwnProperty(key)) {
            if (votes[key] < minVote) {
                participants = [];
                let participant = key;
                minVote = votes[key];
                participants.push({ [key]: votes[participant] });
            }

            else if (votes[key] === minVote) {
                let participant = key;
                participants.push({ [key]: votes[participant] });
            }
        }
    }
    return participants;
}

function CheckVotes(lessOrMore, votes, participant) {
    let vote = votes;
    if (vote.length == 1) {
        participant.innerText = "il partecipante con " + lessOrMore + " voti é: \n";
    }

    else if (vote.length > 1) {
        participant.innerText = "i partecipanti con " + lessOrMore + " voti sono: \n";
    }

    else {
        return;
    }

    let index = 0;
    for (let couple in vote) {
        let firstKey = Object.keys(votes[couple])[0];
        let firstValue = votes[couple][firstKey];
        participant.innerText += firstKey + ": " + firstValue + "\n";
        index++;
    }
    participant.innerText += "\n";
}

function GetNumOfParticipants() {
    const keys = Object.keys(votes);
    numOfParticipants.innerText = "il numero di partecipanti è: " + keys.length;
}


var numOfParticipants = document.getElementById("gnop");
var participantWithMoreVotes = document.getElementById("pwmv");
var participantWithLessVotes = document.getElementById("pwlv");

var maxVote = GetParticipantWithMoreVotes();
let numOfVotes = maxVote[0];
let keys = Object.keys(votes);
let value = votes[keys[0]];

var minVote = GetParticipantWithLessVotes(value);

CheckVotes("più", maxVote, participantWithMoreVotes);
CheckVotes("meno", minVote, participantWithLessVotes);
GetNumOfParticipants();