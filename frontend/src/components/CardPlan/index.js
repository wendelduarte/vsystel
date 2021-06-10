import {
    Card,
    CardBody,
    CardText,
  } from 'styled-card-component';
import './styles.css';

const CardPlan = (props) => (
    <Card className='card'>
      <CardBody>
        <CardText>
          {props.name}
        </CardText>
      </CardBody>
    </Card>
  );

  export default CardPlan;