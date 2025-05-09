import { useEffect, useState } from 'react';
import axios from 'axios';

const LatestRequests = () => {
  const [requests, setRequests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch the latest N requests from the backend API using axios
    axios.get('http://localhost:8081/api/view/latest')
      .then(response => {
        setRequests(response.data);
        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        setError('Error fetching latest requests');
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
      <h2 className="text-xl font-semibold mb-2">Latest Requests</h2>
      <table className="min-w-full">
        <thead>
          <tr>
            <th className="py-2 px-4 text-left">Request ID</th>
            <th className="py-2 px-4 text-left">Operation</th>
            <th className="py-2 px-4 text-left">Status</th>
          </tr>
        </thead>
        <tbody>
          {requests.map(request => (
            <tr key={request.id}>
              <td className="py-2 px-4">{request.id}</td>
              <td className="py-2 px-4">{request.operation}</td>
              <td className="py-2 px-4">{request.status}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default LatestRequests;
