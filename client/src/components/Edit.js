import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Edit extends Component {

    constructor(props) {
        super(props);
        this.state = {
            project: {}
        };
    }

    componentDidMount() {
        axios.get('http://localhost:9191/project/'+this.props.match.params.id)
            .then(res => {
                this.setState({ project: res.data });
                console.log(this.state.project);
            });
    }

    onChange = (e) => {
        const state = this.state.project
        state[e.target.name] = e.target.value;
        this.setState({project:state});
    }

    onSubmit = (e) => {
        e.preventDefault();

        const { name, description, state } = this.state.project;

        axios.put('http://localhost:9191/project/'+this.props.match.params.id, { name, description, state})
            .then((result) => {
                this.props.history.push("/show/"+this.props.match.params.id)
            });
    }

    render() {
        return (
            <div className="container">
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <h3 className="panel-title">
                            EDIT PROJECT
                        </h3>
                    </div>
                    <div className="panel-body">
                        <h4><Link to={`/show/${this.state.project.id}`}><span className="glyphicon glyphicon-eye-open" aria-hidden="true"></span> Project List</Link></h4>
                        <form onSubmit={this.onSubmit}>
                            <div className="form-group">
                                <label htmlFor="name">Name:</label>
                                <input type="text" className="form-control" name="name" value={this.state.project.name} onChange={this.onChange} placeholder="Name" />
                            </div>
                            <div className="form-group">
                                <label htmlFor="title">Description:</label>
                                <input type="text" className="form-control" name="description" value={this.state.project.description} onChange={this.onChange} placeholder="Description" />
                            </div>
                            <div className="form-group">
                                <label htmlFor="author">State:</label>
                                <input type="text" className="form-control" name="state" value={this.state.project.state} onChange={this.onChange} placeholder="State" />
                            </div>
                            {/*<div className="form-group">*/}
                                {/*<label for="description">Postal Code:</label>*/}
                                {/*<input type="number" className="form-control" name="postalCode" value={this.state.customer.postalCode} onChange={this.onChange} placeholder="Postal Code" />*/}
                            {/*</div>*/}
                            {/*<div className="form-group">*/}
                                {/*<label for="published_date">Phone Number:</label>*/}
                                {/*<input type="number" className="form-control" name="phone" value={this.state.customer.phone} onChange={this.onChange} placeholder="Phone Number" />*/}
                            {/*</div>*/}
                            <button type="submit" className="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Edit;