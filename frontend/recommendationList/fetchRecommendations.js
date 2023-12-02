export default function fetchRecommendations() {
  return fetch("/api/recommendations").then(response => {
    if (response.ok)
        return response.json();

        throw new Error("Something went wrong: " + response.statusText);
  });
}
  