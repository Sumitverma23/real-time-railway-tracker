// API Configuration
const API_BASE_URL = window.location.origin;

// Get DOM elements
const elements = {
    form: document.getElementById('searchForm'),
    sourceCode: document.getElementById('sourceCode'),
    destinationCode: document.getElementById('destinationCode'),
    loader: document.getElementById('loader'),
    error: document.getElementById('error'),
    results: document.getElementById('results')
};

// Form submit event listener
elements.form.addEventListener('submit', handleSearch);

// Handle form submission
async function handleSearch(e) {
    e.preventDefault();
    
    const sourceCode = elements.sourceCode.value.trim().toUpperCase();
    const destinationCode = elements.destinationCode.value.trim().toUpperCase();
    
    // Validation
    if (!sourceCode || !destinationCode) {
        showError('Please enter both source and destination station codes.');
        return;
    }
    
    if (sourceCode === destinationCode) {
        showError('Source and destination cannot be the same.');
        return;
    }
    
    await searchTrains(sourceCode, destinationCode);
}

// Search trains from API
async function searchTrains(sourceCode, destinationCode) {
    showLoader();
    hideError();
    clearResults();
    
    try {
        const url = `${API_BASE_URL}/search/by-code?sourceCode=${encodeURIComponent(sourceCode)}&destinationCode=${encodeURIComponent(destinationCode)}`;
        
        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const data = await response.json();
        
        hideLoader();
        displayResults(data);
        
    } catch (error) {
        hideLoader();
        showError(`Failed to fetch trains: ${error.message}. Please check if the API is running on ${API_BASE_URL}`);
        console.error('Error fetching trains:', error);
    }
}

// Display search results
function displayResults(trains) {
    if (!trains || trains.length === 0) {
        displayNoResults();
        return;
    }
    
    elements.results.innerHTML = trains.map(train => createTrainCard(train)).join('');
}

// Create train card HTML
function createTrainCard(train) {
    const duration = calculateDuration(train.departureTime, train.arrivalTime);
    
    return `
        <div class="train-card">
            <div class="train-header">
                <div class="train-info">
                    <h3>${capitalizeWords(train.train.trainName)}</h3>
                    <span class="train-number">${train.train.trainNumber}</span>
                </div>
            </div>
            
            <div class="journey-details">
                <div class="station from-station">
                    <div class="station-name">${capitalizeWords(train.source.stationName)}</div>
                    <div class="station-code">${train.source.stationCode}</div>
                    <div class="time">${formatTime(train.departureTime)}</div>
                </div>
                
                <div class="journey-line">
                    <svg width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
                        <path d="M5 12h14"/>
                        <path d="M12 5l7 7-7 7"/>
                    </svg>
                    <span class="duration">${duration}</span>
                </div>
                
                <div class="station to-station">
                    <div class="station-name">${capitalizeWords(train.destination.stationName)}</div>
                    <div class="station-code">${train.destination.stationCode}</div>
                    <div class="time">${formatTime(train.arrivalTime)}</div>
                </div>
            </div>
        </div>
    `;
}

// Display no results message
function displayNoResults() {
    elements.results.innerHTML = `
        <div class="no-results">
            <svg width="80" height="80" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.5">
                <circle cx="11" cy="11" r="8"/>
                <path d="m21 21-4.35-4.35"/>
                <line x1="11" y1="8" x2="11" y2="14"/>
                <line x1="8" y1="11" x2="14" y2="11"/>
            </svg>
            <h3>No Trains Found</h3>
            <p>We couldn't find any trains for this route. Please try different station codes.</p>
        </div>
    `;
}

// Calculate journey duration
function calculateDuration(departureTime, arrivalTime) {
    const [depHour, depMin] = departureTime.split(':').map(Number);
    const [arrHour, arrMin] = arrivalTime.split(':').map(Number);
    
    let totalMinutes = (arrHour * 60 + arrMin) - (depHour * 60 + depMin);
    
    // Handle next day arrival
    if (totalMinutes < 0) {
        totalMinutes += 24 * 60;
    }
    
    const hours = Math.floor(totalMinutes / 60);
    const minutes = totalMinutes % 60;
    
    return `${hours}h ${minutes}m`;
}

// Format time to 12-hour format
function formatTime(time) {
    const [hour, minute] = time.split(':');
    const h = parseInt(hour);
    const period = h >= 12 ? 'PM' : 'AM';
    const displayHour = h % 12 || 12;
    
    return `${displayHour}:${minute} ${period}`;
}

// Capitalize first letter of each word
function capitalizeWords(str) {
    return str.split(' ')
        .map(word => word.charAt(0).toUpperCase() + word.slice(1).toLowerCase())
        .join(' ');
}

// UI Helper functions
function showLoader() {
    elements.loader.classList.remove('hidden');
}

function hideLoader() {
    elements.loader.classList.add('hidden');
}

function showError(message) {
    elements.error.textContent = message;
    elements.error.classList.remove('hidden');
}

function hideError() {
    elements.error.classList.add('hidden');
}

function clearResults() {
    elements.results.innerHTML = '';
}

// Auto-uppercase station codes
elements.sourceCode.addEventListener('input', function(e) {
    e.target.value = e.target.value.toUpperCase();
});

elements.destinationCode.addEventListener('input', function(e) {
    e.target.value = e.target.value.toUpperCase();
});