export default function fetchCurrentUser(setCurrUser, setShowButton) {
  const getCurrUser = async () => {
    setShowButton(false);
    setCurrUser(null);
    try {
      
      const response = await fetch(
        "/api/users/current"
      );
      if (!response.ok) {
        throw new Error(
          "You can't access this data without being logged in."
        );
        
      }
      let userData = await response.json();
      setCurrUser(userData);
      setShowButton(true);
    } catch(err) {
      console.log(err);
      setCurrUser(null);
      setShowButton(false);
    }
    
  }
  getCurrUser()
  }
    