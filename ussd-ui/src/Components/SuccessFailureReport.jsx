import { useEffect, useState } from 'react';
import axios from 'axios';

const SuccessFailureReport = () => {
  // State to store the report data, loading status, and errors
  const [report, setReport] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch data on component mount
  useEffect(() => {
    // Fetch success and failure count from backend API
    axios.get('http://localhost:8081/api/view/summary')
      .then(response => {
        console.log('SuccessFailureReport API Response:', response.data);
        setReport(response.data);
        setLoading(false);
      })
      .catch(err => {
        console.error('Error fetching summary:', err);
        setError('Unable to fetch report data. Please try again later.');
        setLoading(false);
      });
  }, []);

  // Refresh data function
  const refreshData = () => {
    setLoading(true);
    axios.get('http://localhost:8081/api/view/summary')
      .then(response => {
        setReport(response.data);
        setLoading(false);
      })
      .catch(err => {
        setError('Unable to fetch report data. Please try again later.',err);
        setLoading(false);
      });
  };

  // Loading state
  if (loading) {
    return (
      <div className="flex justify-center items-center h-full">
        <div className="text-gray-500 text-lg animate-pulse">Loading...</div>
      </div>
    );
  }

  // Error state
  if (error) {
    return (
      <div className="text-center text-red-500 font-semibold">
        {error}
      </div>
    );
  }

  // Destructuring SUCCESS and FAILED counts
  const success = report.find(item => item.status === 'SUCCESS')?.count || 0;
  const failure = report.find(item => item.status === 'FAILED')?.count || 0;

  return (
    <div className="space-y-6">
      {/* Interactive Header */}
      <div className="bg-gradient-to-r from-indigo-600 to-purple-600 p-6 rounded-xl shadow-lg flex justify-between items-center">
        <h2 className="text-3xl font-semibold text-white">Success / Failure Report</h2>
        <button 
          onClick={refreshData}
          className="px-4 py-2 text-white bg-gray-800 hover:bg-gray-700 rounded-lg shadow-lg transition duration-300"
        >
          Refresh Data
        </button>
      </div>

      {/* Success / Failure Report Cards */}
      <div className="flex justify-between gap-8">
        {/* Success Section */}
        <div className="flex-1 p-6 bg-green-100 rounded-lg shadow-lg transform transition hover:scale-105 hover:shadow-xl">
          <h3 className="text-xl text-green-800 font-medium">Success</h3>
          <p className="text-3xl font-semibold text-green-600">{success}</p>
        </div>

        {/* Failure Section */}
        <div className="flex-1 p-6 bg-red-100 rounded-lg shadow-lg transform transition hover:scale-105 hover:shadow-xl">
          <h3 className="text-xl text-red-800 font-medium">Failure</h3>
          <p className="text-3xl font-semibold text-red-600">{failure}</p>
        </div>
      </div>

      {/* Latest Requests Section (Reverted) */}
      <div className="bg-white p-6 rounded-lg shadow-lg">
        <h3 className="text-xl font-semibold text-gray-800 mb-4">Latest Requests</h3>
        {/* Reverted Simple Layout for Latest Requests */}
        <div className="space-y-4">
          <div className="bg-gray-50 p-4 rounded-lg shadow-md">
            <p className="text-lg text-gray-700">Request #1: Successful operation</p>
            <p className="text-sm text-gray-500">Date: 2025-05-08</p>
          </div>
          <div className="bg-gray-50 p-4 rounded-lg shadow-md">
            <p className="text-lg text-gray-700">Request #2: Failed operation</p>
            <p className="text-sm text-gray-500">Date: 2025-05-08</p>
          </div>
          <div className="bg-gray-50 p-4 rounded-lg shadow-md">
            <p className="text-lg text-gray-700">Request #3: Successful operation</p>
            <p className="text-sm text-gray-500">Date: 2025-05-08</p>
          </div>
        </div>
      </div>

      {/* Optional report summary */}
      <div className="mt-6 text-center">
        <p className="text-sm text-gray-600">
          The above values represent the number of successful and failed operations.
        </p>
      </div>
    </div>
  );
};

export default SuccessFailureReport;
