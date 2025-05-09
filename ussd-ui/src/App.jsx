import SuccessFailureReport from './Components/SuccessFailureReport';
import LatestRequests from './Components/LatestRequests';
import Blacklist from './Components/Blacklist';

const App = () => {
  return (
    <div className="min-h-screen bg-gray-100 p-8">
      <div className="max-w-4xl mx-auto space-y-8">
        {/* Title/Heading */}
        <h1 className="text-3xl font-bold text-center text-gray-800">
          USSD Analytics Dashboard
        </h1>

        {/* Success/Failure Report */}
        <section className="bg-white p-6 rounded-lg shadow-md">
          <SuccessFailureReport />
        </section>

        {/* Latest N Requests */}
        <section className="bg-white p-6 rounded-lg shadow-md">
          <LatestRequests />
        </section>

        {/* Blacklisted Requests */}
        <section className="bg-white p-6 rounded-lg shadow-md">
          <Blacklist />
        </section>
      </div>
    </div>
  );
};

export default App;
