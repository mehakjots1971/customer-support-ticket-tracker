import React from 'react';
import TicketItem from '../TicketItem/TicketItem';
import PropTypes from 'prop-types';

const TicketList = ({ tickets, loading, onStatusUpdate }) => {
  if (loading) {
    return (
      <div className="flex justify-center items-center py-10">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600"></div>
      </div>
    );
  }

  if (tickets.length === 0) {
    return (
      <div className="text-center py-10 bg-white rounded-lg border border-dashed border-gray-300">
        <p className="text-gray-500">No tickets found. Create one to get started!</p>
      </div>
    );
  }

  return (
    <div className="space-y-4">
      {tickets.map((ticket) => (
        <TicketItem 
          key={ticket.id} 
          ticket={ticket} 
          onStatusUpdate={onStatusUpdate} 
        />
      ))}
    </div>
  );
};

TicketList.propTypes = {
  tickets: PropTypes.array.isRequired,
  loading: PropTypes.bool.isRequired,
  onStatusUpdate: PropTypes.func.isRequired
};

export default TicketList;
