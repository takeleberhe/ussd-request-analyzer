import { useEffect, useState } from 'react';
import axios from 'axios';

const Blacklist = () => {
  const [blacklist, setBlacklist] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch blacklisted MSISDNs from the backend API using axios
    axios.get('http://localhost:8081/api/view/fraud')
      .then(response => {
        setBlacklist(response.data);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setError('Error fetching blacklist');
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div className="text-red-500">{error}</div>;
  }

  return (
    <div className="p-4 bg-white shadow-md rounded-lg mt-4">
      <h2 className="text-xl font-semibold mb-2">Blacklisted MSISDNs</h2>
      <ul>
        {blacklist.map(msisdn => (
          <li key={msisdn} className="py-1">{msisdn}</li>
        ))}
      </ul>
    </div>
  );
};

export default Blacklist;
