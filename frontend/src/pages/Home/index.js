import React, { useEffect, useState } from 'react'
import PriceTable from '../../components/PriceTable';
import CardPlan from '../../components/CardPlan';
import PageDefault from '../../components/PageDefault';
import api from '../../service/api';
import './styles.css'
import BasicPagination from '../../components/BasicPagination';
 
function Home() {

  const [plan, setPlan] = useState([]);
  const [prices, setPrices] = useState([]);

  function HandlePlan(){       
    useEffect(() => {
      try {
        api.get('/plans/call').then(response => {
          setPlan(response.data);     
        });
      } catch (err) {
          alert("Falha ao obter os planos");
      }
    }, []);
  }

  function HandlePrices(){       
    useEffect(() => {
      try {
        api.get('/plans/call/price-list').then(response => {
          setPrices(response.data);     
        });
      } catch (err) {
          alert("Falha ao obter os preços");
      }
    }, []);
  }
  
  return (
      <PageDefault>
        {HandlePlan()}
        {HandlePrices()}
        <h1>Confira nossos planos:</h1>     
        <table>
          <tbody>
            <tr>
              {plan.map((plan) => {
                return (
                  <td key={plan.id}>
                    <CardPlan name={plan.description}/>
                  </td>
                )
              })}
            </tr>
          </tbody>
        </table>

        <h1>Tabela de valores:</h1>
        <PriceTable
          tableHeaders={['Origem', 'Destino', '$/min']}
          prices={prices}
        />

        <h1>Calcule o valor da sua ligação:</h1>     
    
        <div>
          <form action="/action_page.php" id="consult-form">
            <label for="origins">Selecione a origem:</label>
            <select id="origins" name="origin-list" form="consult-form">
            {prices.map((price) => {
              return (
                <option value={price.originAreaCode}>
                      0{price.originAreaCode}
                    </option>
                  )
                })}
          </select>
          <label for="targets">Selecione o destino:</label>
            <select id="targets" name="target-list" form="consult-form">
            {prices.map((price) => {
              return (
                <option value={price.targetAreaCode}>
                      0{price.targetAreaCode}
                    </option>
                  )
                })}
          </select>
          <label for="plans">Selecione o plano:</label>
            <select id="plans" name="plans-list" form="consult-form">
            {plan.map((plan) => {
              return (
                <option value={plan.id}>
                      {plan.description}
                    </option>
                  )
                })}
          </select>
          <label for="call-curation">Digite o tempo da chamada:</label>
          <input type="number" id="call-curation" name="call-curation" placeholder='Tempo da chamada'/>
          <input type="submit" value="Consultar"/>
          </form>
        </div>

        <h1>Histórico de consultas:</h1>
        <BasicPagination/>
      </PageDefault>  
  );
}

export default Home;
