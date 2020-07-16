import React from "react";
import "./App.css";
import axios from "axios";

export default class Main extends React.Component {
  flavour;
  quantity;
  constructor() {
    super();
    this.state = {
      flavour: "",
      quantity: 0,
      load: false,
      data: [],
    };
    this.handleChange = this.handleChange.bind(this);
    this.submit = this.submit.bind(this);
  }

  submit(e) {
    e.preventDefault();

    const payload = {
      flavour: this.state.flavour,
      quantity: this.state.quantity,
    };
    console.log(payload);

    axios({
      url: "http://localhost:8082/kafka/placeorder",
      data: payload,
      method: "post",
    })
      .then((res) => {
        window.alert(res.data);
        window.location.replace("http://localhost:3000/get");
      })
      .catch((err) => window.alert(err.message));

    this.setState({ flavour: "" });
    this.setState({ quantity: 0 });
  }
  handleChange(event) {
    const { name, value } = event.target;
    console.log("ds");
    this.setState({
      name: value,
    });
  }

  render() {
    return (
      <div>
        <h1>Milkshake Factory</h1>
        <form onSubmit={this.submit}>
          <select
            value={this.state.flavour}
            name="flavour"
            onChange={(e) => this.setState({ flavour: e.target.value })}
          >
            <option value="">-- Choose your flavour --</option>
            <option value="oreo">Oreo</option>
            <option value="kitkat">Kitkat</option>
            <option value="chocolate">Chocolate</option>
          </select>{" "}
          <input
            name="quantity"
            value={this.state.quantity}
            onChange={(e) => this.setState({ quantity: e.target.value })}
            type="number"
          />
          <input type="submit" value="submit" />
        </form>
      </div>
    );
  }
}
