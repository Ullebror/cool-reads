export default function fetchCategories() {
    return fetch("/api/categories").then(response => {
        if (response.ok)
            return response.json();
    
            throw new Error("Something went wrong: " + response.statusText);
      });
  }