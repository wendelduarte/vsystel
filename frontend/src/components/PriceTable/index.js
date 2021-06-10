import React from 'react';
import './styles.css';

function PriceTable(props){
    return (
        <table className='price-table'>
            <tbody>
            
                <tr>
                    {props.tableHeaders.map((header) => {
                       return(<th id={header}>{header}</th>)
                    })}
                </tr>
            {props.prices.map((price) => {
                return (
                    <tr key={price.id}>
                    <td>
                        {price.originAreaCode}
                    </td>
                    <td>
                        {price.targetAreaCode}
                    </td>
                    <td>
                        {price.pricePerMinute}
                    </td>
                    </tr>
                )
            })}
        </tbody>
    </table>
    );
}

export default PriceTable;