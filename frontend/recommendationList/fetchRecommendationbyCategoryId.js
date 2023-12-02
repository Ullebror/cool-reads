export default function fetchRecommendationbyCategoryId(id) {
    return fetch(`/api/categories/${id}/recommendations`).then(response => {
        if (response.ok)
            return response.json();
    
            throw new Error("Something went wrong: " + response.statusText);
      });
}