import React, { useState } from 'react';
import ticketService from '../../services/ticketService';
import PropTypes from 'prop-types';

const TicketItem = ({ ticket, onStatusUpdate }) => {
  const [updating, setUpdating] = useState(false);

  const handleStatusChange = async (e) => {
    const newStatus = e.target.value;
    setUpdating(true);
    try {
      await ticketService.updateTicketStatus(ticket.id, newStatus);
      onStatusUpdate(); 
    } catch (error) {
      console.error("Failed to update status", error);
      alert("Failed to update status. Please try again.");
    } finally {
      setUpdating(false);
    }
  };

  const getPriorityColor = (priority) => {
    switch (priority) {
      case 'HIGH': return 'bg-red-100 text-red-800';
      case 'MEDIUM': return 'bg-yellow-100 text-yellow-800';
      case 'LOW': return 'bg-green-100 text-green-800';
      default: return 'bg-gray-100 text-gray-800';
    }
  };

  const getStatusColor = (status) => {
    switch (status) {
      case 'OPEN': return 'bg-blue-100 text-blue-800';
      case 'IN_PROGRESS': return 'bg-purple-100 text-purple-800';
      case 'RESOLVED': return 'bg-green-100 text-green-800';
      default: return 'bg-gray-100 text-gray-800';
    }
  };

  return (
    <div className="bg-white p-4 sm:p-5 rounded-xl shadow-sm border border-gray-100 hover:shadow-md hover:border-blue-200 transition-all duration-200 group">
      <div className="flex flex-col sm:flex-row justify-between items-start mb-3 gap-3">
        <div className="flex-1 w-full">
          <h3 className="text-base sm:text-lg font-bold text-gray-900 group-hover:text-blue-600 transition-colors line-clamp-2">{ticket.subject}</h3>
          <p className="text-xs sm:text-sm text-gray-500 mt-1">#{ticket.id} • <span className="font-medium text-gray-700">{ticket.customerName}</span></p>
        </div>
        <div className="flex flex-wrap gap-2">
          <span className={`px-2 py-0.5 rounded-full text-[10px] sm:text-xs font-semibold tracking-wide uppercase ${getPriorityColor(ticket.priority)}`}>
            {ticket.priority}
          </span>
          <span className={`px-2 py-0.5 rounded-full text-[10px] sm:text-xs font-semibold tracking-wide uppercase ${getStatusColor(ticket.status)}`}>
            {ticket.status}
          </span>
        </div>
      </div>
      
      <p className="text-gray-600 mb-5 text-sm leading-relaxed">{ticket.description}</p>
      
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center text-[10px] sm:text-xs text-gray-400 border-t border-gray-50 pt-4 mt-auto gap-4">
        <span className="order-2 sm:order-1">
          Created {new Date(ticket.createdAt).toLocaleDateString()} at {new Date(ticket.createdAt).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'})}
        </span>
        
        <div className="flex flex-col sm:flex-row items-start sm:items-center gap-2 sm:gap-3 w-full sm:w-auto order-1 sm:order-2">
          <label className="text-gray-500 font-semibold whitespace-nowrap">Status Update:</label>
          <div className="relative w-full sm:w-auto">
            <select
              value={ticket.status}
              onChange={handleStatusChange}
              disabled={updating}
              className={`appearance-none border border-gray-200 rounded-lg pl-3 pr-8 py-1.5 text-xs sm:text-sm bg-gray-50 hover:bg-white hover:border-blue-300 focus:ring-2 focus:ring-blue-100 focus:border-blue-400 outline-none transition-all cursor-pointer w-full sm:w-auto ${
                updating ? 'opacity-50 cursor-wait' : ''
              }`}
            >
              <option value="OPEN">Open</option>
              <option value="IN_PROGRESS">In Progress</option>
              <option value="RESOLVED">Resolved</option>
            </select>
            <div className="pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-500">
              <svg className="h-4 w-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 9l-7 7-7-7" />
              </svg>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

TicketItem.propTypes = {
  ticket: PropTypes.object.isRequired,
  onStatusUpdate: PropTypes.func.isRequired
};

export default TicketItem;
